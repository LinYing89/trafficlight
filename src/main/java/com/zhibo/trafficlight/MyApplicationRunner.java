package com.zhibo.trafficlight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.zhibo.trafficlight.service.DetailsAddressService;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    /**
     * 缓存是否初始化完成, 未完成是不需加载页面和接收报文
     */
    public static boolean CACHE_INIT_COMPLETE = false;
    
    @Autowired
    private DetailsAddressService detailsAddressService;
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//容器启动完成后初始化缓存
	    CACHE_INIT_COMPLETE = false;
	    detailsAddressService.findAll();
	    CACHE_INIT_COMPLETE = true;
	}

}