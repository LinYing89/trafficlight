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
import com.zhibo.trafficlight.mapper.CircuitMapper;

@Service
public class CircuitService {

    @Autowired
    private CircuitService self;
    @Autowired
    private CircuitMapper circuitMapper;
    @Autowired
    private CollectorService collectorService;
    
    @CachePut(value="circuit", key="#result.id")
    public Circuit insert(Circuit circuit) {
        Collector collector = collectorService.findById(circuit.getCollectorId());
        collector.addCircuit(circuit);
        
        circuitMapper.insert(circuit);
        return circuit;
    }
    
    @CacheEvict(value="circuit", key="#circuitId")
    public void deleteById(long circuitId) {
        circuitMapper.deleteById(circuitId);
    }
    
    public void update(Circuit circuit) {
        circuitMapper.update(circuit);
    }
    
    public List<Circuit> findByCollectorId(Long collectorId){
        List<Circuit> list = new ArrayList<>();
        List<Circuit> circuits = circuitMapper.findByCollectorId(collectorId);
        for(Circuit c : circuits) {
            Circuit cir = self.findById(c.getId());
            if(null != cir) {
                list.add(cir);
            }
        }
        return list;
    }

    @Cacheable(value="circuit", key="#id")
    public Circuit findById(Long id) {
        return circuitMapper.findById(id);
    }
}
