package com.zhibo.trafficlight.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhibo.trafficlight.MyApplicationRunner;
import com.zhibo.trafficlight.data.Config;
import com.zhibo.trafficlight.data.DetailsAddress;
import com.zhibo.trafficlight.dto.AddressInfo;
import com.zhibo.trafficlight.dto.AreaDTO;
import com.zhibo.trafficlight.dto.DataGroup;
import com.zhibo.trafficlight.service.AreaService;
import com.zhibo.trafficlight.service.DataGroupService;
import com.zhibo.trafficlight.service.DetailsAddressService;

@Controller
public class MapController {

    @Autowired
    private DetailsAddressService detailsAddressService;
    @Autowired
    private Config config;
    @Autowired
    private AreaService areaService;
    @Autowired
    private DataGroupService dataGroupService;

    @GetMapping("/")
    public String map(HttpServletRequest request, Model model) {
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return "";
        }
        HttpSession session = request.getSession();
        int districtId = -1;
        Object obj = session.getAttribute("districtId");
        if (null != obj) {
            districtId = (int) obj;
        }
        model.addAttribute("districtId", districtId);
        return "map/map";
    }

    @GetMapping(value = { "/map/infowidow/{detailsAddressId}" })
    public String infowidow(@PathVariable Long detailsAddressId, Model model) {
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return "";
        }
        DetailsAddress da = detailsAddressService.findById(detailsAddressId);
        DataGroup dataGroup = dataGroupService.createDataGroup(da);
        model.addAttribute("dataGroup", dataGroup);
        model.addAttribute("currentWarnValue", config.getCurrentWarnValue());
        return "dataGroup.html";
    }

    /**
     * 获取所有已添加的省份, 包含市\区
     * 
     * @return
     */
    @ResponseBody
    @GetMapping("/provinces/{districtId}")
    public List<AreaDTO> findProvinces(@PathVariable Integer districtId) {
        return areaService.findProvinces(districtId);
    }

//    @ResponseBody
//    @PostMapping("/addAddress")
//    public Result<AddressInfo> addAddress(@ModelAttribute AddressInfo addressInfo) {
//        return areaService.addAddress(addressInfo);
//    }
    @PostMapping("/addAddress")
    public String addAddress(@ModelAttribute AddressInfo addressInfo, HttpServletRequest request) {
        areaService.addAddress(addressInfo);
        if (addressInfo.getDistrictId() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("districtId", addressInfo.getDistrictId());
        }
        return "redirect:/";
    }
    
    @PostMapping("/edit/detailsAddress/{detailsAddressId}")
    public String editDetailsAddress(@PathVariable Long detailsAddressId, @RequestParam String detailsAddress) {
        areaService.editDetailsAddress(detailsAddressId, detailsAddress);
        return "redirect:/detailsAddress/" + detailsAddressId;
    }

    @ResponseBody
    @PostMapping("/area/detailsAddress")
    public List<AddressInfo> findDetailsAddresses(@RequestParam Integer districtId, HttpServletRequest request) {
        if(!MyApplicationRunner.CACHE_INIT_COMPLETE) {
            return null;
        }
        HttpSession session = request.getSession();
        session.setAttribute("districtId", districtId);
        return areaService.findDetailsAddress(districtId);
    }
    
    @GetMapping("/del/detailsAddress/{detailsAddressId}")
    public String deleteDetailsAddress(@PathVariable long detailsAddressId) {
        DetailsAddress da = detailsAddressService.findById(detailsAddressId);
        detailsAddressService.deleteById(detailsAddressId);
        return "redirect:/data/" + da.getDistrictId();
    }
}
