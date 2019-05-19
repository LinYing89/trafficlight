package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.TrafficLight2;

public interface TrafficLightMapper2 {

    void insert(TrafficLight2 trafficLight);
    void deleteById(long trafficLightId);
    
    void update(TrafficLight2 trafficLight);
    
    List<TrafficLight2> findByDetailsAddressId(Long detailsAddressId);
    
    TrafficLight2 findById(Long id);

}
