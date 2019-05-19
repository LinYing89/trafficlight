package com.zhibo.trafficlight.data;

/**
 * 数据类型
 * @author 44489
 * @version 2019年4月25日上午10:10:40
 */
public enum DateTypeEnum {

	SWITCH_OUT(1, "开关量输出"),
	SWITCH_IN(2, "开关量输入"),
	KEEP_REGISTER(3, "保持寄存器"),
	IN_REGISTER(4, "输入寄存器");
	
	private int code;
	private String info;
	
	DateTypeEnum(int code, String info){
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
