package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.zhibo.trafficlight.data.Circuit2;
import com.zhibo.trafficlight.mapper.CircuitMapper2;

public class Circuit2Service {

    @Autowired
    private Circuit2Service self;
    @Autowired
    private CircuitMapper2 circuitMapper2;
    
    @CachePut(value = "circuit2", key = "#result.id")
    public void insert(Circuit2 circuit) {
        circuitMapper2.insert(circuit);
    }
    
    @CacheEvict(value = "circuit2", key = "#circuitId")
    public void deleteById(long circuitId) {
        circuitMapper2.deleteById(circuitId);
    }
    
    public void update(Circuit2 circuit) {
        circuitMapper2.update(circuit);
    }
    
    public List<Circuit2> findByDetailsAddressId(Long detailsAddressId){
        List<Circuit2> list = circuitMapper2.findByDetailsAddressId(detailsAddressId);
        List<Circuit2> directions = new ArrayList<>();
        for(Circuit2 d : list) {
            directions.add(self.findById(d.getId()));
        }
        return list;
    }
    
    @Cacheable(value = "circuit2", key = "#id")
    public Circuit2 findById(Long id) {
        Circuit2 d = circuitMapper2.findById(id);
        return d;
    }
}
