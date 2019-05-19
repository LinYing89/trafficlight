package com.zhibo.trafficlight.data;

/**
 * 方向
 * 
 * @author 44489
 * @version 2019年5月15日上午8:40:49
 */
public class Direction2 implements Comparable<Direction2>{

    private Long id;
    // 方向名称
    private String name;
    // 方向排序索引
    private Integer sortIndex;
    // 详细地址id
    private Long detailsAddressId;
    // 详细地址
    private DetailsAddress detailsAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
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

    @Override
    public int compareTo(Direction2 o) {
        if(null == o) {
            return 0;
        }
        return this.sortIndex.compareTo(o.getSortIndex());
    }
}
