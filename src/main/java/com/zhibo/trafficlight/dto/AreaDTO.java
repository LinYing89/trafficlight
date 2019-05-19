package com.zhibo.trafficlight.dto;

import java.util.ArrayList;
import java.util.List;

public class AreaDTO {

    private Integer areaId;
    private String areaName;
    private Integer parentId;
    private List<AreaDTO> childArea = new ArrayList<>();
    private boolean selected = false;
    
    public Integer getAreaId() {
        return areaId;
    }
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public List<AreaDTO> getChildArea() {
        return childArea;
    }
    public void setChildArea(List<AreaDTO> childArea) {
        this.childArea = childArea;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
