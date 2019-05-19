package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhibo.trafficlight.data.Direction2;
import com.zhibo.trafficlight.mapper.DirectionMapper2;

@Service
public class Direction2Service {

    @Autowired
    private Direction2Service self;
    @Autowired
    private DirectionMapper2 directionMapper2;
    
    @CachePut(value = "direction2", key = "#result.id")
    public Direction2 insert(Direction2 direction) {
        directionMapper2.insert(direction);
        return direction;
    }
    
    @CacheEvict(value = "direction2", key = "#directionId")
    public void deleteById(long directionId) {
        directionMapper2.deleteById(directionId);
    }
    
    public void update(Direction2 direction) {
        directionMapper2.update(direction);
    }
    
    public List<Direction2> findByDetailsAddressId(Long detailsAddressId){
        List<Direction2> list = directionMapper2.findByDetailsAddressId(detailsAddressId);
        List<Direction2> directions = new ArrayList<>();
        for(Direction2 d : list) {
            directions.add(self.findById(d.getId()));
        }
        Collections.sort(directions);
        return list;
    }
    
    @Cacheable(value = "direction2", key = "#id")
    public Direction2 findById(Long id) {
        Direction2 d = directionMapper2.findById(id);
        return d;
    }
}
