package com.zhibo.trafficlight.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 通信管理机
 * 
 * @author LinQiang
 *
 */
public class MsgManager {

    private Long id;
    // 通信机编码
    private Integer code;
    // 详细地址id
    private Long detailsAddressId;
    private DetailsAddress DetailsAddress;

    // 采集终端
    private List<Collector> collectors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getDetailsAddressId() {
        return detailsAddressId;
    }

    public void setDetailsAddressId(Long detailsAddressId) {
        this.detailsAddressId = detailsAddressId;
    }

    public DetailsAddress getDetailsAddress() {
        return DetailsAddress;
    }

    public void setDetailsAddress(DetailsAddress detailsAddress) {
        DetailsAddress = detailsAddress;
    }

    public List<Collector> getCollectors() {
        return collectors;
    }
//	public void setCollectors(List<Collector> collectors) {
//		this.collectors = collectors;
//	}

    public Collector findCollectorByCode(int collectorCode) {
        for (Collector c : collectors) {
            if (c.getCode() == collectorCode) {
                return c;
            }
        }
        return null;
    }

    public void addCollector(Collector collector) {
        if (null != collector) {
            collectors.add(collector);
            collector.setMsgManagerId(id);
            collector.setMsgManager(this);
        }
    }

    public void removeCollector(Collector collector) {
        if (null != collector) {
            collector.setMsgManager(null);
            collector.setMsgManagerId(null);
            collectors.remove(collector);
        }
    }
}
