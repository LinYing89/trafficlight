package com.zhibo.trafficlight.data;

/**
 * 信号灯
 * @author 44489
 * @version 2019年4月27日下午4:17:01
 */
public class TrafficLight {

    //状态
    private Integer state;
    //电流
    private Integer current;
    //颜色
    private LightEnum lightEnum;
    //开关时间
    private Integer switchingTime;
    
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
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
    public Integer getSwitchingTime() {
        return switchingTime;
    }
    public void setSwitchingTime(Integer switchingTime) {
        this.switchingTime = switchingTime;
    }
}
