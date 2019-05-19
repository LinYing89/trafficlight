package com.zhibo.trafficlight.dto;

public class AddressInfo {

    //省
    private String province;
    //市
    private String city;
    //区
    private String district;
    private Integer districtId;
    //详细信息
    private String detailsAddress;
    private Long detailsAddressId;
    private double lng;
    private double lat;
    
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getDistrictId() {
        return districtId;
    }
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getDetailsAddress() {
        return detailsAddress;
    }
    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
    public Long getDetailsAddressId() {
        return detailsAddressId;
    }
    public void setDetailsAddressId(Long detailsAddressId) {
        this.detailsAddressId = detailsAddressId;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
}
