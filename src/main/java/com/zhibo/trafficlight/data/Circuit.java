package com.zhibo.trafficlight.data;

/**
 * 电路
 * @author 44489
 * @version 2019年4月25日上午10:53:34
 */
public class Circuit {

	private Long id;
	//第几路
	private Integer number;
	//信号灯选择
	private LightEnum lightEnum;
	//方向选择
	private DirectionEnum directionEnum;
	private Long collectorId;
	
	private Collector collector;
	
	//状态, 0正常, 1异常
	private Integer state;
	//开关时间
	private Integer switchingTime;
	//电流, mA
	private Integer current;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSwitchingTime() {
		return switchingTime;
	}
	public void setSwitchingTime(Integer switchingTime) {
		this.switchingTime = switchingTime;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public LightEnum getLightEnum() {
		return lightEnum;
	}
	public void setLightEnum(LightEnum lightEnum) {
		this.lightEnum = lightEnum;
	}
	public DirectionEnum getDirectionEnum() {
		return directionEnum;
	}
	public void setDirectionEnum(DirectionEnum directionEnum) {
		this.directionEnum = directionEnum;
	}
	public Long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	public Collector getCollector() {
		return collector;
	}
	public void setCollector(Collector collector) {
		this.collector = collector;
	}
	
}
