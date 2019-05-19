package com.zhibo.trafficlight.data;

/**
 * 线路
 * 
 * @author 44489
 * @version 2019年5月15日上午10:22:12
 */
public class Circuit2 {

    private Long id;
    // 第几路
    private Integer number;
    // 行索引
    private Integer rowIndex;
    // 列索引
    private Integer columnIndex;
    // 通信机id
    private Long msgManagerId;
    private Integer msgManagerCode;
    // 采集终端id
    private Long collectorId;
    private Integer collectorCode;
    // 详细地址id
    private Long detailsAddressId;
    // 详细地址
    private DetailsAddress detailsAddress;

    // 状态, 0正常, 1异常
    private Integer state;
    // 开时间
    private Integer onTime;
    // 关时间
    private Integer offTime;
    // 电流, mA
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

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Long getMsgManagerId() {
        return msgManagerId;
    }

    public void setMsgManagerId(Long msgManagerId) {
        this.msgManagerId = msgManagerId;
    }

    public Integer getMsgManagerCode() {
        return msgManagerCode;
    }

    public void setMsgManagerCode(Integer msgManagerCode) {
        this.msgManagerCode = msgManagerCode;
    }

    public Long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Long collectorId) {
        this.collectorId = collectorId;
    }

    public Integer getCollectorCode() {
        return collectorCode;
    }

    public void setCollectorCode(Integer collectorCode) {
        this.collectorCode = collectorCode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOnTime() {
        return onTime;
    }

    public void setOnTime(Integer onTime) {
        this.onTime = onTime;
    }

    public Integer getOffTime() {
        return offTime;
    }

    public void setOffTime(Integer offTime) {
        this.offTime = offTime;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Long getDetailsAddressId() {
        return detailsAddressId;
    }

    public void setDetailsAddressId(Long detailsAddressId) {
        this.detailsAddressId = detailsAddressId;
    }

    public DetailsAddress getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(DetailsAddress detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
}
