package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.mapper.DetailsAddressMapper;

@Service
public class DetailsAddressService {

    @Resource
    private DetailsAddressService self;
    @Autowired
    private DetailsAddressMapper detailsAddressMapper;
    @Autowired
    private MsgManagerService msgManagerService;
    
    @CachePut(value="detailsAddress", key="#result.id")
    public DetailsAddress insert(DetailsAddress detailsAddress) {
        detailsAddressMapper.insert(detailsAddress);
        return detailsAddress;
    }
    
    @CacheEvict(value="detailsAddress", key="#id")
    public void deleteById(Long id) {
        DetailsAddress da = self.findById(id);
        for(MsgManager mm : da.getMsgManagers()) {
            msgManagerService.deleteById(mm.getId());
        }
        detailsAddressMapper.deleteById(id);
    }
    
    public void updateDetailsAddressById(Long id, String detailsAddress) {
        DetailsAddress da = self.findById(id);
        if(null != da) {
            da.setDetailsAddress(detailsAddress);
            detailsAddressMapper.updateDetailsAddressById(id, detailsAddress);
        }
    }
    
    @Cacheable(value="detailsAddress", key="#id")
    public DetailsAddress findById(Long id) {
        DetailsAddress da = detailsAddressMapper.findById(id);
        List<MsgManager> msgManagers = msgManagerService.findByDetailsAddressId(da.getId());
        for(MsgManager mm : msgManagers) {
            da.addMsgManager(mm);
        }
        return da;
    }
    
    public List<DetailsAddress> findByDistrictId(Integer districtId) {
        List<DetailsAddress> list = new ArrayList<>();
        List<DetailsAddress> listdb = detailsAddressMapper.findByDistrictId(districtId);
        for(DetailsAddress da : listdb) {
            list.add(self.findById(da.getId()));
        }
        return list;
    }
    
    public List<DetailsAddress> findAll() {
        List<DetailsAddress> list = new ArrayList<>();
        List<DetailsAddress> listdb = detailsAddressMapper.findAll();
        for(DetailsAddress da : listdb) {
            list.add(self.findById(da.getId()));
        }
        return list;
    }
}
