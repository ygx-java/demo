package com.core.solr.controller;

import java.io.IOException;
import java.util.UUID;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.databackup.dto.Person;
import com.core.solr.service.SolrService;

@RestController
@RequestMapping("/solr")
public class SolrController {
	
	@Autowired
	private SolrService solrService;
	
	@RequestMapping("/add")
	public Person add(){
		Person person = new Person();
		person.setId(UUID.randomUUID().toString());
		person.setName("李白");
		person.setPassword("123");
		person.setTitle("诗人");
		person.setAge("18");
		person.setAddress("天津市");
		solrService.addDoc(person);
		return person;
	}
	
	//删除所有
	@RequestMapping("/delAllDoc")
	public void delAllDoc(){
		solrService.delAllDoc();
	}
	
	//查询
	@RequestMapping("/search")
	public SolrDocumentList search(String name) throws SolrServerException, IOException {
		Person person = new Person();
		person.setName(name);
//		person.setPassword("123");
//		person.setTitle("诗人");
//		person.setAge("18");
//		person.setAddress("天津市");
		SolrDocumentList search = solrService.query(person);
//		for (SolrDocument doc : search) {
//            System.out.println("id:"+doc.get("id")+"   name:"+doc.get("name")+"    description:"+doc.get("description"));
//        }
		return search;
	}
	
}
