package com.zhibo.trafficlight.data;

/**
 * 地址
 * @author 44489
 * @version 2019年4月25日上午9:06:02
 */
public class Address {

	//省
	private Area province;
	//市
	private Area city;
	//区
	private Area district;
	//详细地址
	private DetailsAddress detailsAddress;
	
	private MsgManager msgManager;

	public Area getProvince() {
		return province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public Area getDistrict() {
		return district;
	}

	public void setDistrict(Area district) {
		this.district = district;
	}

	public DetailsAddress getDetailsAddress() {
		return detailsAddress;
	}

	public void setDetailsAddress(DetailsAddress detailsAddress) {
		this.detailsAddress = detailsAddress;
	}

	public MsgManager getMsgManager() {
		return msgManager;
	}

	public void setMsgManager(MsgManager msgManager) {
		this.msgManager = msgManager;
	}
	
}
