package com.zhibo.trafficlight.data;

/**
 * 信号灯枚举
 * @author 44489
 * @version 2019年4月25日上午10:39:11
 */
public enum LightEnum {

	//红灯
	RED(0, "红"),
	//黄灯
	YELLOW(1, "黄"),
	//绿灯
	GREEN(2, "绿"),
  //绿灯
    PEDESTRIAN(3, "人行红灯");
    
    private int code;
    private String info;
    
    LightEnum(int code, String info){
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
