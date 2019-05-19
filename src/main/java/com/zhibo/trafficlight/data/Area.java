package com.zhibo.trafficlight.data;

/**
 * 省\市\区对象
 * @author 44489
 * @version 2019年4月25日上午9:23:19
 */
public class Area {

	private Integer areaId;
	
	//区域代码
	private String areaCode;
	//名称
	private String areaName;
	//级别, 1=省, 2=市, 3=区
	private Integer level;
	//区号, 连云港=0518
	private String cityCode;
	//区域中心的经纬度
	private String center;
	//上级id, 省没有上级为-1
	private Integer parentId;
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
