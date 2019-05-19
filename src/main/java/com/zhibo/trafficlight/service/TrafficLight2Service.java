package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhibo.trafficlight.data.TrafficLight2;
import com.zhibo.trafficlight.mapper.TrafficLightMapper2;

@Service
public class TrafficLight2Service {

    @Autowired
    private TrafficLight2Service self;
    @Autowired
    private TrafficLightMapper2 trafficLightMapper2;
    
    @CachePut(value = "trafficLight2", key = "#result.id")
    public TrafficLight2 insert(TrafficLight2 trafficLight) {
        trafficLightMapper2.insert(trafficLight);
        return trafficLight;
    }
    
    @CacheEvict(value = "trafficLight2", key = "#trafficLightId")
    public void deleteById(long trafficLightId) {
        trafficLightMapper2.deleteById(trafficLightId);
    }
    
    public void update(TrafficLight2 trafficLight) {
        trafficLightMapper2.update(trafficLight);
    }
    
    public List<TrafficLight2> findByDetailsAddressId(Long detailsAddressId){
        List<TrafficLight2> list = trafficLightMapper2.findByDetailsAddressId(detailsAddressId);
        List<TrafficLight2> trafficLights = new ArrayList<>();
        for(TrafficLight2 d : list) {
            trafficLights.add(self.findById(d.getId()));
        }
        Collections.sort(trafficLights);
        return list;
    }
    
    @Cacheable(value = "trafficLight2", key = "#id")
    public TrafficLight2 findById(Long id) {
        TrafficLight2 d = trafficLightMapper2.findById(id);
        return d;
    }
}
