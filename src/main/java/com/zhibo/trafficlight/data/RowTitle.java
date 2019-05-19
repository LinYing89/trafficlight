package com.zhibo.trafficlight.data;

import java.util.List;

/**
 * 数据表表头
 * @author 44489
 * @version 2019年5月17日上午9:54:14
 */
public class RowTitle {

    //列名称集合
    private List<String> titles;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
    
    public void addTitle(String title) {
        titles.add(title);
    }
}
