package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.DetailsAddress;

public interface DetailsAddressMapper {

    void insert(DetailsAddress detailsAddress);
    
    void deleteById(Long id);
    
    void updateDetailsAddressById(Long id, String detailsAddress);
    
    DetailsAddress findById(Long id);
    
    List<DetailsAddress> findByDistrictId(Integer districtId);
    
    List<DetailsAddress> findAll();
}
