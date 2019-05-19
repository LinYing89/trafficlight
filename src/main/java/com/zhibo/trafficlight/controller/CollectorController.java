package com.zhibo.trafficlight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zhibo.trafficlight.data.Area;
import com.zhibo.trafficlight.data.Collector;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.service.AreaService;
import com.zhibo.trafficlight.service.CollectorService;
import com.zhibo.trafficlight.service.MsgManagerService;

@Controller
public class CollectorController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private MsgManagerService msgManagerService;
    @Autowired
    private CollectorService collectorService;
    
    @GetMapping("/msgManager/{msgManagerId}")
    public String collectors(@PathVariable Long msgManagerId, Model model) {
        MsgManager mm = msgManagerService.findById(msgManagerId);
        model.addAttribute("msgManager", mm);
        
        DetailsAddress da = mm.getDetailsAddress();
        Area district = areaService.findByAreaId(da.getDistrictId());
        Area city = areaService.findByAreaId(district.getParentId());
        Area provine = areaService.findByAreaId(city.getParentId());
        String areaName = String.format("%s-%s-%s-%s", provine.getAreaName(), city.getAreaName(), district.getAreaName(), da.getDetailsAddress());
        model.addAttribute("areaName", areaName);
        
        return "device/msgManager";
    }
    
    @PostMapping("/add/collector/{msgManagerId}")
    public String addMsgManager(@PathVariable Long msgManagerId, @ModelAttribute Collector collector) {
        collector.setMsgManagerId(msgManagerId);
        collectorService.insert(collector);
        return "redirect:/msgManager/" + msgManagerId;
    }
    
    @GetMapping("/del/collector/{collectorId}")
    public String delCollector(@PathVariable Long collectorId) {
        Collector collector = collectorService.findById(collectorId);
        MsgManager mm = collector.getMsgManager();
        collectorService.deleteById(collectorId);
        mm.removeCollector(collector);
        return "redirect:/msgManager/" + mm.getId();
    }
    
    @PostMapping("/edit/collector/{collectorId}")
    public String editMsgManager(@PathVariable long collectorId, @ModelAttribute Collector collector) {
        Collector collectordb = collectorService.findById(collectorId);
        collector.setId(collectorId);
        MsgManager mm = collectordb.getMsgManager();
        collectorService.updateCollector(collector);
        return "redirect:/msgManager/" + mm.getId();
    }
    
}
