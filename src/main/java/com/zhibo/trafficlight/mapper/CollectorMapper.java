package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.Collector;

public interface CollectorMapper {

    void insert(Collector collector);
    void deleteById(long collectorId);
    /**
     * 更新通信管理机编号
     * @param msgManagerId
     * @param code
     */
    void update(Collector collector);
    
    List<Collector> findByMsgManagerId(Long msgManagerId);

    Collector findById(Long id);
}
