package com.zhibo.trafficlight.data;

/**
 * 方向枚举
 * @author 44489
 * @version 2019年4月25日上午10:40:28
 */
public enum DirectionEnum {

	/**
	 * 东向西
	 */
	EAST_WEST(0, "东向西"),
	WEST_EAST(1, "西向东"),
	SOUTH_NORTH(2, "南向北"),
	NORTH_SOUTH(3, "北向南");
	
	private int code;
	private String info;
	
	DirectionEnum(int code, String info){
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
