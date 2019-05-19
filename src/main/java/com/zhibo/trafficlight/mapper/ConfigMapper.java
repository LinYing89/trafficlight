package com.zhibo.trafficlight.mapper;

import com.zhibo.trafficlight.data.Config;

public interface ConfigMapper {
    
    void updateCurrentWarnValue(Integer value, Long id);
    void update(Config config);
    Config findById(Long id);
}
