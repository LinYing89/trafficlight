package com.zhibo.trafficlight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zhibo.trafficlight.data.Direction2;
import com.zhibo.trafficlight.service.Direction2Service;

@Controller
public class DirectionController {

    @Autowired
    private Direction2Service direction2Service;
    
    @PostMapping("/add/direction/{detailsAddressId}")
    public String addDirection(@PathVariable Long detailsAddressId, @ModelAttribute Direction2 direction) {
        direction.setDetailsAddressId(detailsAddressId);
        direction2Service.insert(direction);
        return "redirect:/detailsAddress/tableStruct/" + detailsAddressId;
    }
    
    @GetMapping("/del/direction/{directionId}")
    public String delDirection(@PathVariable Long directionId) {
        Direction2 d = direction2Service.findById(directionId);
        direction2Service.deleteById(directionId);
        return "redirect:/detailsAddress/tableStruct/" + d.getDetailsAddressId();
    }
}
