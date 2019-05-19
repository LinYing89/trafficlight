package com.zhibo.trafficlight.data;

/**
 * 配置
 * @author 44489
 * @version 2019年5月6日上午10:06:30
 */
public class Config {
    
    private Long id;
    //线路电流报警值
    private Integer currentWarnValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentWarnValue() {
        return currentWarnValue;
    }

    public void setCurrentWarnValue(Integer currentWarnValue) {
        this.currentWarnValue = currentWarnValue;
    }
}
