package com.core.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.mongodb.dao.CustomerRepository;
import com.core.mongodb.dto.Customer;
import com.core.mongodb.util.MongoDbUtil;

@RequestMapping("/mongo")
@Controller
public class MongoController {

//	@Autowired
//	private CustomerRepository repository;
	
	//mongodb测试
//	@RequestMapping("/test")
//	@ResponseBody
//	public void test() {
		
//		repository.deleteAll();
//
//		// save a couple of customers
//		repository.save(new Customer("Alice", "Smith"));
//		repository.save(new Customer("Bob", "Smith"));
//
//		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (Customer customer : repository.findAll()) {
//			System.out.println(customer);
//		}
//		System.out.println();
//
//		// fetch an individual customer
//		System.out.println("Customer found with findByFirstName('Alice'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByFirstName("Alice"));
//
//		System.out.println("Customers found with findByLastName('Smith'):");
//		System.out.println("--------------------------------");
//		for (Customer customer : repository.findByLastName("Smith")) {
//			System.out.println(customer);
//		}
//	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public String test2() {
		MongoDbUtil instance = MongoDbUtil.getInstance();
		List<String> allCollections = instance.getAllCollections("test");
		for (String str : allCollections) {
			System.out.println(str);
		}
		return null;
	}
}
