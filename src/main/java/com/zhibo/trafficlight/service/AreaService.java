package com.zhibo.trafficlight.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhibo.msgmanager_util.Result;
import com.zhibo.trafficlight.MyApplicationRunner;
import com.zhibo.trafficlight.ResultEnum;
import com.zhibo.trafficlight.data.Area;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.dto.AddressInfo;
import com.zhibo.trafficlight.dto.AreaDTO;
import com.zhibo.trafficlight.mapper.AreaMapper;

@Service
public class AreaService {

    private static final Logger logger = LoggerFactory.getLogger(AreaService.class);
    
    @Resource
    private AreaService self;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private DetailsAddressService detailsAddressService;
    
    public Integer findAreaIdByAreaName(String areaName) {
        return areaMapper.findAreaIdByAreaName(areaName);
    }
    
    public Integer findAreaIdByAreaNameAndParentId(String areaName, Integer parentId) {
        return areaMapper.findAreaIdByAreaNameAndParentId(areaName, parentId);
    }
    
    public Area findByAreaId(Integer areaId) {
        return areaMapper.findByAreaId(areaId);
    }
    
    /**
     * 添加一个地址
     * @param addressInfo
     * @return
     */
    public Result<AddressInfo> addAddress(AddressInfo addressInfo) {
        Result<AddressInfo> result = new Result<>();
        Integer provinceId = self.findAreaIdByAreaName(addressInfo.getProvince());
        if (null == provinceId) {
            result.setCode(ResultEnum.UNKNOW_DISTRICT.getCode());
            result.setMsg(ResultEnum.UNKNOW_DISTRICT.getMessage() + " : province - " + addressInfo.getProvince());
            logger.error(result.getMsg());
            return result;
        }
        Integer cityId = self.findAreaIdByAreaNameAndParentId(addressInfo.getCity(), provinceId);
        if (null == cityId) {
            result.setCode(ResultEnum.UNKNOW_DISTRICT.getCode());
            result.setMsg(ResultEnum.UNKNOW_DISTRICT.getMessage() + " : city - " + addressInfo.getProvince());
            logger.error(result.getMsg());
            return result;
        }
        Integer districtId = self.findAreaIdByAreaNameAndParentId(addressInfo.getDistrict(), cityId);
        if (null == districtId) {
            result.setCode(ResultEnum.UNKNOW_DISTRICT.getCode());
            result.setMsg(ResultEnum.UNKNOW_DISTRICT.getMessage() + " : district - " + addressInfo.getProvince());
            logger.error(result.getMsg());
            return result;
        }
        addressInfo.setDistrictId(districtId);

        DetailsAddress detailsAddress = new DetailsAddress();
        detailsAddress.setDistrictId(districtId);
        detailsAddress.setDetailsAddress(addressInfo.getDetailsAddress());
        detailsAddress.setLongitude(addressInfo.getLng());
        detailsAddress.setLatitude(addressInfo.getLat());
        detailsAddressService.insert(detailsAddress);
        addressInfo.setDetailsAddressId(detailsAddress.getId());
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setData(addressInfo);
        return result;
    }
    
    public void editDetailsAddress(long detailsAddressId, String detailsAddress) {
        detailsAddressService.updateDetailsAddressById(detailsAddressId, detailsAddress);
    }
    
    public List<AddressInfo> findDetailsAddress(Integer districtId){
        List<DetailsAddress> detailsAddresses = detailsAddressService.findByDistrictId(districtId);
        List<AddressInfo> list = new ArrayList<>();
        for(DetailsAddress da : detailsAddresses) {
            AddressInfo dto = new AddressInfo();
            dto.setDistrictId(da.getDistrictId());
            dto.setDetailsAddressId(da.getId());
            dto.setDetailsAddress(da.getDetailsAddress());
            dto.setLng(da.getLongitude());
            dto.setLat(da.getLatitude());
            list.add(dto);
        }
        return list;
    }
    
    /**
     * 获取所有已添加的省市区信息
     * @param districtsId 选择的区id, 如果不需要则可以传-1
     * @return
     */
    public List<AreaDTO> findProvinces(int districtsId){
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return null;
        }
        List<DetailsAddress> detailsAddresses = detailsAddressService.findAll();
        //找区/县
        List<AreaDTO> districts = new ArrayList<>();
        //因为区id可能重复, 保存唯一id
        List<Integer> districtIds = new ArrayList<>();
        for(DetailsAddress da : detailsAddresses) {
            addAreaIdToList(districtIds, da.getDistrictId());
        }
        //选择的市id
        int selectedCityId = -1;
        for(Integer areaId : districtIds) {
            Area district = self.findByAreaId(areaId);
            if(null != district) {
                AreaDTO areaDTO = new AreaDTO();
                areaDTO.setAreaId(district.getAreaId());
                areaDTO.setAreaName(district.getAreaName());
                areaDTO.setParentId(district.getParentId());
                districts.add(areaDTO);
                if(districtsId != -1 && districtsId == district.getAreaId()) {
                    areaDTO.setSelected(true);
                    selectedCityId = areaDTO.getParentId();
                }
            }
        }
        //找市
        List<AreaDTO> cities = new ArrayList<>();
        //地址id去重复
        List<Integer> cityIds = new ArrayList<>();
        for(AreaDTO district : districts) {
            addAreaIdToList(cityIds, district.getParentId());
        }
      //选择的省id
        int selectedProvinceId = -1;
        for(Integer areaId : cityIds) {
            Area city = self.findByAreaId(areaId);
            if(null != city) {
                AreaDTO areaDTO = new AreaDTO();
                areaDTO.setAreaId(city.getAreaId());
                areaDTO.setAreaName(city.getAreaName());
                areaDTO.setParentId(city.getParentId());
                for(AreaDTO district : districts) {
                    if(district.getParentId().equals(areaId)) {
                        areaDTO.getChildArea().add(district);
                    }
                }
                cities.add(areaDTO);
                
                if(selectedCityId != -1 && selectedCityId == city.getAreaId()) {
                    areaDTO.setSelected(true);
                    selectedProvinceId = areaDTO.getParentId();
                }
            }
        }
        //找省
        List<AreaDTO> provinces = new ArrayList<>();
        //地址id去重复
        List<Integer> provinceIds = new ArrayList<>();
        for(AreaDTO city : cities) {
            addAreaIdToList(provinceIds, city.getParentId());
        }
        for(Integer provinceId : provinceIds) {
            Area province = self.findByAreaId(provinceId);
            if(null != province) {
                AreaDTO areaDTO = new AreaDTO();
                areaDTO.setAreaId(province.getAreaId());
                areaDTO.setAreaName(province.getAreaName());
                for(AreaDTO city : cities) {
                    if(city.getParentId().equals(provinceId)) {
                        areaDTO.getChildArea().add(city);
                    }
                }
                provinces.add(areaDTO);
                
                if(selectedProvinceId != -1 && selectedProvinceId == province.getAreaId()) {
                    areaDTO.setSelected(true);
                }
            }
        }
        return provinces;
    }
    
    /**
     * 将地址id添加到id集合, 去重复, 如果集合中已有, 则不添加
     * @param areaIds
     * @param id
     */
    private void addAreaIdToList(List<Integer> areaIds, Integer id) {
        for(Integer areaId : areaIds) {
            if(areaId.equals(id)) {
                return;
            }
        }
        areaIds.add(id);
    }
}
