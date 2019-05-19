package com.zhibo.trafficlight.dto;

import com.zhibo.trafficlight.data.Direction;
import com.zhibo.trafficlight.data.DirectionEnum;

/**
 * 数据组, 对应一个详细地址的数据, 一个卡片组
 * @author 44489
 * @version 2019年4月27日下午4:26:52
 */
public class DataGroup {
    
    // 详细地址
    private String detailsAddress = "";
    private Long detailsAddressId;

    //东西方向
    private Direction eastWestDirection;
    //西东方向
    private Direction westEastDirection;
    //南北方向
    private Direction southNorthDirection;
    //北南方向
    private Direction northSouthDirection;
    
    public DataGroup() {
        eastWestDirection = new Direction();
        eastWestDirection.setDirectionEnum(DirectionEnum.EAST_WEST);
        westEastDirection = new Direction();
        westEastDirection.setDirectionEnum(DirectionEnum.WEST_EAST);
        southNorthDirection = new Direction();
        southNorthDirection.setDirectionEnum(DirectionEnum.SOUTH_NORTH);
        northSouthDirection = new Direction();
        northSouthDirection.setDirectionEnum(DirectionEnum.NORTH_SOUTH);
    }
    
    public String getDetailsAddress() {
        return detailsAddress;
    }
    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
    public Long getDetailsAddressId() {
        return detailsAddressId;
    }
    public void setDetailsAddressId(Long detailsAddressId) {
        this.detailsAddressId = detailsAddressId;
    }
    public Direction getEastWestDirection() {
        return eastWestDirection;
    }
    public void setEastWestDirection(Direction eastWestDirection) {
        this.eastWestDirection = eastWestDirection;
    }
    public Direction getWestEastDirection() {
        return westEastDirection;
    }
    public void setWestEastDirection(Direction westEastDirection) {
        this.westEastDirection = westEastDirection;
    }
    public Direction getSouthNorthDirection() {
        return southNorthDirection;
    }
    public void setSouthNorthDirection(Direction southNorthDirection) {
        this.southNorthDirection = southNorthDirection;
    }
    public Direction getNorthSouthDirection() {
        return northSouthDirection;
    }
    public void setNorthSouthDirection(Direction northSouthDirection) {
        this.northSouthDirection = northSouthDirection;
    }
    
}
