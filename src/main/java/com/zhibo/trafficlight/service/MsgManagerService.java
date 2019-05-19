package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhibo.msgmanager_util.Result;
import com.zhibo.trafficlight.ResultEnum;
import com.zhibo.trafficlight.data.Collector;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.mapper.MsgManagerMapper;

@Service
public class MsgManagerService {

    @Autowired
    private MsgManagerService self;
    @Autowired
    private MsgManagerMapper msgManagerMapper;
    @Autowired
    private CollectorService collectorService;
    @Autowired
    private DetailsAddressService detailsAddressService;

    public Result<Object> addMsgManager(int msgManagerCode, long detailsAddressId){
        Result<Object> result = new Result<>();
        MsgManager mm = self.findByCode(msgManagerCode);
        if(mm != null) {
            result.setCode(ResultEnum.MSG_MANAGER_CODE_HAVED.getCode());
            result.setMsg(ResultEnum.MSG_MANAGER_CODE_HAVED.getMessage());
            return result;
        }
        self.insert(msgManagerCode, detailsAddressId);
        result.setCode(ResultEnum.SUCCESS.getCode());
        return result;
    }
    
    @CachePut(value = "msgManager", key = "#result.code")
    public MsgManager insert(int msgManagerCode, long detailsAddressId) {
        MsgManager mm = new MsgManager();
        mm.setCode(msgManagerCode);
        mm.setDetailsAddressId(detailsAddressId);

        DetailsAddress da = detailsAddressService.findById(detailsAddressId);
        da.addMsgManager(mm);

        msgManagerMapper.insert(mm);
        return mm;
    }

    @CacheEvict(value = "msgManager", condition = "#result != null", key = "#result.code")
    public MsgManager deleteById(long msgManagerId) {
        MsgManager mm = self.findById(msgManagerId);
        if(null != mm) {
            msgManagerMapper.deleteById(msgManagerId);
            for(Collector c : mm.getCollectors()) {
                collectorService.deleteById(c.getId());
            }
        }
        return mm;
    }
    
    public Result<Object> editMsgManager(long msgManagerId, int msgManagerCode) {
        Result<Object> result = new Result<>();
        MsgManager mm = self.findByCode(msgManagerCode);
        if(mm != null) {
            result.setCode(ResultEnum.MSG_MANAGER_CODE_HAVED.getCode());
            result.setMsg(ResultEnum.MSG_MANAGER_CODE_HAVED.getMessage());
            return result;
        }
        MsgManager mm1 = self.findById(msgManagerId);
        mm1.setCode(msgManagerCode);
        msgManagerMapper.updateMsgManagerCode(mm1.getId(), msgManagerCode);
        result.setCode(ResultEnum.SUCCESS.getCode());
        return result;
    }

    @Cacheable(value = "msgManager", key = "#code")
    public MsgManager findByCode(int code) {
        MsgManager mm = msgManagerMapper.findByCode(code);
        if (null != mm) {
            List<Collector> collectors = collectorService.findByMsgManagerId(mm.getId());
            for(Collector collector : collectors) {
                mm.addCollector(collector);
            }
        }
        return mm;
    }

    public MsgManager findById(long id) {
        MsgManager mm = msgManagerMapper.findById(id);
        MsgManager mm1 = self.findByCode(mm.getCode());
        return mm1;
    }

    public List<MsgManager> findByDetailsAddressId(long detailsAddressId) {
        List<MsgManager> list = new ArrayList<>();
        List<MsgManager> listdb = msgManagerMapper.findByDetailsAddressId(detailsAddressId);
        for (MsgManager mm : listdb) {
            list.add(self.findByCode(mm.getCode()));
        }
        return list;
    }
}
