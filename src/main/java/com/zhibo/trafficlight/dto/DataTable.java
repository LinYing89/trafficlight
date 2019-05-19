package com.zhibo.trafficlight.dto;

import java.util.ArrayList;
import java.util.List;

import com.zhibo.trafficlight.data.RowDirection;
import com.zhibo.trafficlight.data.RowTitle;

/**
 * 数据表
 * @author 44489
 * @version 2019年5月17日上午9:52:23
 */
public class DataTable {

    //表头行
    private RowTitle rowTitle;
    //方向行
    private List<RowDirection> rowDirections = new ArrayList<>();
    
    public RowTitle getRowTitle() {
        return rowTitle;
    }
    public void setRowTitle(RowTitle rowTitle) {
        this.rowTitle = rowTitle;
    }
    public List<RowDirection> getRowDirections() {
        return rowDirections;
    }
    public void setRowDirections(List<RowDirection> rowDirections) {
        this.rowDirections = rowDirections;
    }
    
    public void addRowDirection(RowDirection rowDirection) {
        rowDirections.add(rowDirection);
    }
}
