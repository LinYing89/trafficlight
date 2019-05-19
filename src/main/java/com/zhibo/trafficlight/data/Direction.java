package com.zhibo.trafficlight.data;

/**
 * 方向
 * 
 * @author 44489
 * @version 2019年4月27日下午4:15:42
 */
public class Direction {

    // 方向
    private DirectionEnum directionEnum;

    // 红灯
    private TrafficLight redLight;
    // 黄灯
    private TrafficLight yellowLight;
    // 绿灯
    private TrafficLight greenLight;
    // 人行红灯
    private TrafficLight pedestrianLight;

    public Direction() {
        redLight = new TrafficLight();
        redLight.setLightEnum(LightEnum.RED);
        yellowLight = new TrafficLight();
        yellowLight.setLightEnum(LightEnum.YELLOW);
        greenLight = new TrafficLight();
        greenLight.setLightEnum(LightEnum.GREEN);
        pedestrianLight = new TrafficLight();
        pedestrianLight.setLightEnum(LightEnum.PEDESTRIAN);
    }

    public DirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(DirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }

    public TrafficLight getRedLight() {
        return redLight;
    }

    public void setRedLight(TrafficLight redLight) {
        this.redLight = redLight;
    }

    public TrafficLight getYellowLight() {
        return yellowLight;
    }

    public void setYellowLight(TrafficLight yellowLight) {
        this.yellowLight = yellowLight;
    }

    public TrafficLight getGreenLight() {
        return greenLight;
    }

    public void setGreenLight(TrafficLight greenLight) {
        this.greenLight = greenLight;
    }

    public TrafficLight getPedestrianLight() {
        return pedestrianLight;
    }

    public void setPedestrianLight(TrafficLight pedestrianLight) {
        this.pedestrianLight = pedestrianLight;
    }
}
