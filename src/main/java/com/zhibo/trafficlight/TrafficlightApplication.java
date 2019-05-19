package com.zhibo.trafficlight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.zhibo.trafficlight.data.Config;
import com.zhibo.trafficlight.mapper.ConfigMapper;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages="com.zhibo.trafficlight.mapper")
public class TrafficlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficlightApplication.class, args);
	}
	
	@Bean
	public Config config(ConfigMapper configMapper) {
	    return configMapper.findById(1L);
	}
}
