package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhibo.trafficlight.data.Circuit;
import com.zhibo.trafficlight.data.Collector;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.mapper.CollectorMapper;

@Service
public class CollectorService {

    @Autowired
    private CollectorService self;
    @Autowired
    private CollectorMapper collectorMapper;
    @Autowired
    private CircuitService circuitService;
    @Autowired
    private MsgManagerService msgManagerService;

    @CachePut(value = "collector", key = "#result.id")
    public Collector insert(Collector collector) {
        MsgManager mm = msgManagerService.findById(collector.getMsgManagerId());
        mm.addCollector(collector);

        collectorMapper.insert(collector);
        return collector;
    }

    @CacheEvict(value = "collector", key = "#collectorId")
    public void deleteById(long collectorId) {
        Collector collector = self.findById(collectorId);
        for(Circuit c : collector.getCircuits()) {
            circuitService.deleteById(c.getId());
        }
        collectorMapper.deleteById(collectorId);
    }

    public void updateCollector(Collector collector) {
        Collector c = self.findById(collector.getId());
        c.setBeginAddress(collector.getBeginAddress());
        c.setBusCode(collector.getBusCode());
        c.setCode(collector.getCode());
        c.setDataLength(collector.getDataLength());
        c.setDataTypeEnum(collector.getDataTypeEnum());
        collectorMapper.update(c);
    }

    public List<Collector> findByMsgManagerId(Long msgManagerId) {
        List<Collector> list = new ArrayList<>();
        List<Collector> listdb = collectorMapper.findByMsgManagerId(msgManagerId);
        for (Collector c : listdb) {
            Collector co = self.findById(c.getId());
            if (null != co) {
                list.add(co);
            }
        }
        return list;
    }

    @Cacheable(value = "collector", key = "#id")
    public Collector findById(Long id) {
        Collector collector = collectorMapper.findById(id);
        if (null != collector) {
            List<Circuit> circuits = circuitService.findByCollectorId(collector.getId());
            for(Circuit circuit : circuits) {
                collector.addCircuit(circuit);
            }
        }
        return collector;
    }
}
