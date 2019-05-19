package com.zhibo.trafficlight.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhibo.trafficlight.MyApplicationRunner;
import com.zhibo.trafficlight.data.Config;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.dto.DataGroup;
import com.zhibo.trafficlight.service.DataGroupService;
import com.zhibo.trafficlight.service.DetailsAddressService;

@Controller
public class DataController {

    @Autowired
    private DetailsAddressService detailsAddressService;
    @Autowired
    private Config config;
    @Autowired
    private DataGroupService dataGroupService;

    /**
     * 数据展示页
     * 
     * @return
     */
    @GetMapping("/data/{districtId}")
    public String data(@PathVariable Integer districtId, Model model) {
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return "";
        }
        List<DataGroup> list = findDataGroups(districtId);
        model.addAttribute("dataGroups", list);
        model.addAttribute("districtId", districtId);
        model.addAttribute("currentWarnValue", config.getCurrentWarnValue());
        return "data2";
    }

    @GetMapping("/dataGroups/{districtId}")
    public String findDataGroups(@PathVariable Integer districtId, Model model) {
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return "";
        }
        List<DataGroup> list = findDataGroups(districtId);
        model.addAttribute("dataGroups", list);
        model.addAttribute("currentWarnValue", config.getCurrentWarnValue());
        return "data2::div-datagroups";
    }

    @ResponseBody
    @GetMapping("/dataGroups/datas/{districtId}")
    public List<DataGroup> getDatas(@PathVariable Integer districtId) {
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return null;
        }
        return findDataGroups(districtId);
    }

    private List<DataGroup> findDataGroups(Integer districtId) {
        List<DataGroup> list = new ArrayList<>();
        List<DetailsAddress> detailsAddresses = detailsAddressService.findByDistrictId(districtId);
        for (DetailsAddress da : detailsAddresses) {
            DataGroup dataGroup = dataGroupService.createDataGroup(da);
            list.add(dataGroup);
        }
        return list;
    }
}
