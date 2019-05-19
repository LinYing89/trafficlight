package com.zhibo.trafficlight.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 详细地址
 * 
 * @author 44489
 * @version 2019年4月25日上午9:38:26
 */
public class DetailsAddress {

    private Long id;
    // 区id
    private Integer districtId;
    // 详细地址
    private String detailsAddress;
    // 经度
    private Double longitude;
    // 纬度
    private Double latitude;

    private List<MsgManager> msgManagers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer areaId) {
        this.districtId = areaId;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public List<MsgManager> getMsgManagers() {
        return msgManagers;
    }

//    public void setMsgManagers(List<MsgManager> msgManagers) {
//        this.msgManagers = msgManagers;
//    }

    public void addMsgManager(MsgManager mm) {
        if (null != mm) {
            msgManagers.add(mm);
            mm.setDetailsAddressId(id);
            mm.setDetailsAddress(this);
        }
    }

    public void removeMsgManager(MsgManager mm) {
        if (null != mm) {
            mm.setDetailsAddress(null);
            mm.setDetailsAddressId(null);
            msgManagers.remove(mm);
        }
    }
}
