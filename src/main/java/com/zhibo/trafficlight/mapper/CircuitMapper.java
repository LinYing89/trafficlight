package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.Circuit;

public interface CircuitMapper {

    void insert(Circuit circuit);
    void deleteById(long circuitId);
    void update(Circuit circuit);
    
    List<Circuit> findByCollectorId(Long collectorId);

    Circuit findById(Long id);
}
