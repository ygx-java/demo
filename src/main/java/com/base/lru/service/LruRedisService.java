package com.base.lru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.redis.util.RedisUtil;

@Service
public class LruRedisService {

	@Autowired
	private RedisUtil redisUtil;
	
	//利用redis实现lru算法
	public void test2(){
		//RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");
		redisUtil.lpush("lru", 1);
		redisUtil.lpush("lru", 2);
		redisUtil.lpush("lru", 3);
		redisUtil.lpush("lru", 4);
		redisUtil.lpush("lru", 5);
//		List<Object> lget = redisUtil.lget("lru",0,-1);
//		for (Object object : lget) {
//			System.out.println(object.toString());
//		}
	}
	
	//
	public void name() {
		
	}
}
