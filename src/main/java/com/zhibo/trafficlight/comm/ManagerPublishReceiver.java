package com.zhibo.trafficlight.comm;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhibo.msgmanager_util.MessageAnalysiser;
import com.zhibo.msgmanager_util.NetMessageAnalysisResult;
import com.zhibo.msgmanager_util.OneMessageAnalysisResult;
import com.zhibo.msgmanager_util.Util;
import com.zhibo.trafficlight.MyApplicationRunner;
import com.zhibo.trafficlight.data.Circuit;
import com.zhibo.trafficlight.data.Collector;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.service.MsgManagerService;

@Component
public class ManagerPublishReceiver {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MsgManagerService msgManagerService;

    private MessageAnalysiser messageAnalysiser;

    public ManagerPublishReceiver() {
        messageAnalysiser = new MessageAnalysiser();
    }

    public void receiveMsg(byte[] msg) {
        String str = Util.bytesToHexString(msg);
        logger.info(str);
        // 长度不可小于8个字节
        if (msg.length < 8) {
            return;
        }
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return;
        }
        analysisMsg(msg);
    }

    public void analysisMsg(byte[] msg) {
        NetMessageAnalysisResult result = messageAnalysiser.analyisis(msg);
        for (OneMessageAnalysisResult oneMsg : result.getListErrorResult()) {
            int managerCode = oneMsg.getManagerCode();
            MsgManager manager = msgManagerService.findByCode(managerCode);
            if (null == manager) {
                continue;
            }
            for (byte[] subOrder : oneMsg.getListSubOrder()) {
                // 解析子报文
                // 采集终端编号
                int index = 1;
                int collectorCode = subOrder[index];
                Collector collector = manager.findCollectorByCode(collectorCode);
                if (null == collector) {
                    continue;
                }
                // 数据长度
                index += 3;
                int dataLength = Util.bytesToInt(new byte[] { subOrder[index], subOrder[index + 1] });
                // 数据
                index += 2;
                byte[] byData = Arrays.copyOfRange(subOrder, index, subOrder.length);
                // 36 = 34(数据) + 类型 + 保留
                if (byData.length < 36 || byData.length != dataLength) {
                    continue;
                }
                int intState = Util.bytesToInt(new byte[] { byData[0], byData[1] });
                for (Circuit circuit : collector.getCircuits()) {
                    int circuitNumber = circuit.getNumber();
                    // 右移线路编号位, 高位转为0
                    int state = intState >> (circuitNumber - 1) & 1;
                    circuit.setState(state);
                    // 状态和电流都是两个字节
                    // 一路对应4个字节
                    // -2是减去状态两个字节
                    int byteStart = circuitNumber * 4 - 2;
                    int switchingTime = Util.bytesToInt(new byte[] { byData[byteStart], byData[byteStart + 1] });
                    int current = Util.bytesToInt(new byte[] { byData[byteStart + 2], byData[byteStart + 3] });
                    circuit.setSwitchingTime(switchingTime);
                    circuit.setCurrent(current);
                }
            }
        }
    }
}
