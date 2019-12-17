package com.core.solr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jms.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.base.databackup.dto.Person;
import com.core.activemq.producer.ActiveProducer;
import com.core.solr.util.SolrUtil;

@Service
public class SolrService implements ActiveProducer{

	@Autowired
	private SolrUtil solrUtil;
	@Autowired
	private JmsTemplate jmsTemplate;

	// 向索引库中填数据
	public void addDoc(Person person) {
		solrUtil.addDoc(person);
	}
	//删除
	public void delDocByID(Person person) {
		solrUtil.delDocByID(person);
	}
	//删除所有
	public void delAllDoc() {
		solrUtil.delAllDoc();
	}
	//查询
	public SolrDocumentList query(Person person) throws SolrServerException, IOException {
		SolrDocumentList solrDocumentList = solrUtil.query(person);
		if (solrDocumentList.isEmpty()) {
			//通过activemq进行缓存更新操作
			this.sendMessage(new ActiveMQTopic("solr_data_backup"), person);
//			this.sendMessage(new ActiveMQQueue("solr_data_backup"), person);
			return null;
		}
		return solrDocumentList;
	}

	@Override
	public void sendMessage(Destination destination, Object message) {
		jmsTemplate.convertAndSend(destination,message);
	}

	@Override
	public void sendMessage(String destination, Object message) {
		
	}
}