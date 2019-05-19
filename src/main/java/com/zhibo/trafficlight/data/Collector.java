package com.zhibo.trafficlight.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 采集终端
 * @author LinQiang
 * 2019年4月18日上午9:17:53
 */
public class Collector {

	private Long id;
	
	//总线号
	private Integer busCode;
	//终端编号
	private Integer code;
	//起始地址
	private Integer beginAddress;
	//数据长度
	private Integer dataLength;
	//数据类型
	private DateTypeEnum dataTypeEnum;
	//保留
	private Integer retain;
	
	//通信管理机id
	private Long msgManagerId;
	private MsgManager msgManager;
	
	private List<Circuit> circuits = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getBusCode() {
		return busCode;
	}
	public void setBusCode(Integer busCode) {
		this.busCode = busCode;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getBeginAddress() {
		return beginAddress;
	}
	public void setBeginAddress(Integer beginAddress) {
		this.beginAddress = beginAddress;
	}
	public Integer getDataLength() {
        return dataLength;
    }
    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }
    public DateTypeEnum getDataTypeEnum() {
        return dataTypeEnum;
    }
    public void setDataTypeEnum(DateTypeEnum dataTypeEnum) {
        this.dataTypeEnum = dataTypeEnum;
    }
    public Integer getRetain() {
		return retain;
	}
	public void setRetain(Integer retain) {
		this.retain = retain;
	}
	public Long getMsgManagerId() {
		return msgManagerId;
	}
	public void setMsgManagerId(Long msgManagerId) {
		this.msgManagerId = msgManagerId;
	}
	public MsgManager getMsgManager() {
		return msgManager;
	}
	public void setMsgManager(MsgManager msgManager) {
		this.msgManager = msgManager;
	}
    public List<Circuit> getCircuits() {
        return circuits;
    }
//    public void setCircuits(List<Circuit> circuits) {
//        this.circuits = circuits;
//    }
    
    public void addCircuit(Circuit circuit) {
        if(null != circuit) {
            circuit.setCollector(this);
            circuit.setCollectorId(id);
            circuits.add(circuit);
        }
    }
    
    public void removeCircuit(Circuit circuit) {
        if(null != circuit) {
            circuit.setCollector(null);
            circuit.setCollectorId(null);
            circuits.remove(circuit);
        }
    }
}
