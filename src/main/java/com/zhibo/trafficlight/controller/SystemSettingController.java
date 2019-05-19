package com.zhibo.trafficlight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhibo.trafficlight.data.Config;
import com.zhibo.trafficlight.mapper.ConfigMapper;

@Controller
@RequestMapping("/systemSetting")
public class SystemSettingController {

    @Autowired
    private Config config;
    @Autowired
    private ConfigMapper configMapper;
    
    @GetMapping("/page")
    private String configPage(Model model) {
        model.addAttribute("config", config);
        return "systemset";
    }
    
    @PostMapping("/page")
    private String config(@ModelAttribute Config config, Model model) {
        this.config.setCurrentWarnValue(config.getCurrentWarnValue());
        configMapper.update(this.config);
        model.addAttribute("config", config);
        return "redirect:/systemSetting/page";
    }
}
