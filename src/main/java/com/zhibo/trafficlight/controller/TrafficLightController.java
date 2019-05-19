package com.zhibo.trafficlight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zhibo.trafficlight.data.TrafficLight2;
import com.zhibo.trafficlight.service.TrafficLight2Service;

@Controller
public class TrafficLightController {

    @Autowired
    private TrafficLight2Service trafficLight2Service;
    
    @PostMapping("/add/trafficLight/{detailsAddressId}")
    public String addDirection(@PathVariable Long detailsAddressId, @ModelAttribute TrafficLight2 trafficLight2) {
        trafficLight2.setDetailsAddressId(detailsAddressId);
        trafficLight2Service.insert(trafficLight2);
        return "redirect:/detailsAddress/tableStruct/" + detailsAddressId;
    }
    
    @GetMapping("/del/trafficLight/{trafficLightId}")
    public String delDirection(@PathVariable Long trafficLightId) {
        TrafficLight2 t = trafficLight2Service.findById(trafficLightId);
        trafficLight2Service.deleteById(trafficLightId);
        return "redirect:/detailsAddress/tableStruct/" + t.getDetailsAddressId();
    }
}
