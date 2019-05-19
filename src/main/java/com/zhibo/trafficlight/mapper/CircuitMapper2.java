package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.Circuit2;

public interface CircuitMapper2 {

    void insert(Circuit2 circuit);
    void deleteById(long circuitId);
    
    void update(Circuit2 circuit);
    
    List<Circuit2> findByDetailsAddressId(Long detailsAddressId);
    
    Circuit2 findById(Long id);
}
