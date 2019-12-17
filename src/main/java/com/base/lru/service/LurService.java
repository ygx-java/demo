package com.base.lru.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.stereotype.Service;
import com.base.lru.dto.LRULinkedHashMap;

@Service
public class LurService {
	
	//利用LinkedHashMap实现lru算法
//	@Test
	public void test() {
		//指定缓存最大容量为4
		Map<Integer,Integer> map=new LRULinkedHashMap<>(4);
		map.put(9,3);
		map.put(7,4);
		map.put(6,9);
		map.put(5,4);
		map.put(4,6);
		map.put(3,6);
		//总共put了5个元素，超过了指定的缓存最大容量
		//遍历结果
		for(Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();it.hasNext();){
			System.out.println(it.next().getKey());
		}
	}
}
	
