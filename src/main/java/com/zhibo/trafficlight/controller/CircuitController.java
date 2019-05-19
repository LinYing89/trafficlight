package com.zhibo.trafficlight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zhibo.trafficlight.data.Area;
import com.zhibo.trafficlight.data.Circuit;
import com.zhibo.trafficlight.data.Collector;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.data.MsgManager;
import com.zhibo.trafficlight.service.AreaService;
import com.zhibo.trafficlight.service.CircuitService;
import com.zhibo.trafficlight.service.CollectorService;

@Controller
public class CircuitController {

    @Autowired
    private AreaService areaService;
    @Autowired
    private CollectorService collectorService;
    @Autowired
    private CircuitService circuitService;
    
    /**
     * 线路页面
     * @param collectorId
     * @param model
     * @return
     */
    @GetMapping("/circuit/{collectorId}")
    public String collectors(@PathVariable Long collectorId, Model model) {
        Collector collector = collectorService.findById(collectorId);
        model.addAttribute("collector", collector);
        
        MsgManager mm = collector.getMsgManager();
        DetailsAddress da = mm.getDetailsAddress();
        Area district = areaService.findByAreaId(da.getDistrictId());
        Area city = areaService.findByAreaId(district.getParentId());
        Area provine = areaService.findByAreaId(city.getParentId());
        String areaName = String.format("%s-%s-%s-%s", provine.getAreaName(), city.getAreaName(), district.getAreaName(), da.getDetailsAddress());
        model.addAttribute("areaName", areaName);
        
        return "device/collector";
    }
    
    @PostMapping("/add/circuit/{collectorId}")
    public String addMsgManager(@PathVariable Long collectorId, @ModelAttribute Circuit circuit) {
        circuit.setCollectorId(collectorId);
        circuitService.insert(circuit);
        return "redirect:/circuit/" + collectorId;
    }
    
    @GetMapping("/del/circuit/{circuitId}")
    public String delMsgManager(@PathVariable Long circuitId) {
        Circuit circuit = circuitService.findById(circuitId);
        circuitService.deleteById(circuitId);
        Collector c = circuit.getCollector();
        c.removeCircuit(circuit);
        return "redirect:/circuit/" + c.getId();
    }
}
