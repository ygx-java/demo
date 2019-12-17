package com.core.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.redis.util.RedisUtil;

@Controller
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping("/test")
	public String test() {
		boolean set = redisUtil.set("redis", "redis");
		return set+"";
	}
	
}
