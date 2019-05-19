package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.Direction2;

public interface DirectionMapper2 {

    void insert(Direction2 direction);
    void deleteById(long directionId);
    
    void update(Direction2 direction);
    
    List<Direction2> findByDetailsAddressId(Long detailsAddressId);
    
    Direction2 findById(Long id);
}
