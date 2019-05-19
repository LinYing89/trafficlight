package com.zhibo.trafficlight.mapper;

import com.zhibo.trafficlight.data.Area;

public interface AreaMapper {

    Integer findAreaIdByAreaName(String name);
    
    Integer findAreaIdByAreaNameAndParentId(String areaName, Integer parentId);
    
    Area findByAreaId(Integer areaId);
}
