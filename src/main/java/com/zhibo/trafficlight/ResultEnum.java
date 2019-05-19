package com.zhibo.trafficlight;

public enum ResultEnum {
    
    UNKNOW(-1, "未知错误"),
    SUCCESS(0, "成功"),
    UNKNOW_DISTRICT(1, "区/县不存在"),
    MSG_MANAGER_CODE_HAVED(2, "已有通信管理机编号, 不可重复添加");
    
    private int code;
    private String message;
    
    private ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
