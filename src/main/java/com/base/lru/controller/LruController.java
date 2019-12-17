package com.base.lru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.lru.service.LruRedisService;
import com.base.lru.service.LurService;

@Controller
@RequestMapping("/lru")
public class LruController {
	
	@Autowired
	private LurService lurService;
	@Autowired
	private LruRedisService lruRedisService;
	
	@RequestMapping("/redisLru")
	public void redisLru() {
		lruRedisService.test2();
	}
}
