package com.zhibo.trafficlight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhibo.msgmanager_util.Result;
import com.zhibo.trafficlight.data.Area;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.data.Direction2;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.data.TrafficLight2;
import com.zhibo.trafficlight.service.AreaService;
import com.zhibo.trafficlight.service.DetailsAddressService;
import com.zhibo.trafficlight.service.Direction2Service;
import com.zhibo.trafficlight.service.MsgManagerService;
import com.zhibo.trafficlight.service.TrafficLight2Service;

@Controller
public class MsgManagerContoller {

    @Autowired
    private AreaService areaService;
    @Autowired
    private DetailsAddressService detailsAddressService;
    @Autowired
    private MsgManagerService msgManagerService;
    @Autowired
    private Direction2Service direction2Service;
    @Autowired
    private TrafficLight2Service trafficLight2Service;
    
    @GetMapping("/detailsAddress/{detailsAddressId}")
    public String DetailsAddressSettings(@PathVariable Long detailsAddressId, Model model) {
        DetailsAddress da = detailsAddressService.findById(detailsAddressId);
        model.addAttribute("detailsAddress", da);
        model.addAttribute("msgManagers", da.getMsgManagers());
        
        Area district = areaService.findByAreaId(da.getDistrictId());
        Area city = areaService.findByAreaId(district.getParentId());
        Area provine = areaService.findByAreaId(city.getParentId());
        String areaName = String.format("%s-%s-%s-%s", provine.getAreaName(), city.getAreaName(), district.getAreaName(), da.getDetailsAddress());
        model.addAttribute("areaName", areaName);
//        return "device/detailsAddress";
        return "device2/detailsAddressSettings";
    }
    
    @GetMapping("/detailsAddress/tableStruct/{detailsAddressId}")
    public String tableStruct(@PathVariable Long detailsAddressId, Model model) {
        DetailsAddress da = detailsAddressService.findById(detailsAddressId);
        model.addAttribute("detailsAddress", da);
        
        List<Direction2> directions = direction2Service.findByDetailsAddressId(detailsAddressId);
        model.addAttribute("directions", directions);
        List<TrafficLight2> trafficLights = trafficLight2Service.findByDetailsAddressId(detailsAddressId);
        model.addAttribute("trafficLights", trafficLights);
        
        Area district = areaService.findByAreaId(da.getDistrictId());
        Area city = areaService.findByAreaId(district.getParentId());
        Area provine = areaService.findByAreaId(city.getParentId());
        String areaName = String.format("%s-%s-%s-%s", provine.getAreaName(), city.getAreaName(), district.getAreaName(), da.getDetailsAddress());
        model.addAttribute("areaName", areaName);
        return "device2/dataTableStruct";
    }
    
    @GetMapping("/get/msgManagers/{detailsAddressId}")
    public String getMsgManagers(@PathVariable Long detailsAddressId, Model model){
        DetailsAddress da = detailsAddressService.findById(detailsAddressId);
        List<MsgManager> msgManagers = da.getMsgManagers();
        model.addAttribute("msgManagers", msgManagers);
        return "device/detailsAddress::tbody-msgmanager";
    }
    
    @ResponseBody
    @PostMapping("/add/msgManager/{detailsAddressId}")
    public Result<Object> addMsgManager(@PathVariable Long detailsAddressId, @RequestParam Integer msgManagerCode) {
        Result<Object> result = msgManagerService.addMsgManager(msgManagerCode, detailsAddressId);
        return result;
    }
    
    @GetMapping("/del/msgManager/{msgManagerId}")
    public String delMsgManager(@PathVariable Long msgManagerId) {
        MsgManager mm = msgManagerService.findById(msgManagerId);
        DetailsAddress da  = mm.getDetailsAddress();
        msgManagerService.deleteById(msgManagerId);
        da.removeMsgManager(mm);
        return "redirect:/detailsAddress/" + da.getId();
    }
    
    @ResponseBody
    @PostMapping("/edit/msgManager/{msgManagerId}")
    public Result<Object> editMsgManager(@PathVariable long msgManagerId, @RequestParam int msgManagerCode) {
        Result<Object> result = msgManagerService.editMsgManager(msgManagerId, msgManagerCode);
        return result;
    }
}
