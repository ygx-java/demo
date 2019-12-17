package com.base.databackup.service;

import java.util.List;
import javax.jms.JMSException;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import com.base.databackup.dao.PersonDao;
import com.base.databackup.dto.Person;
import com.core.solr.util.SolrUtil;

@Service
public class DataBackupsService {
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private SolrUtil solrUtil;

	//查询索引库,若没有这从数据库查询,并插入到索引库
	@JmsListener(destination = "solr_data_backup")
	public void receiveQueue(Message msg) throws JMSException {
		if (msg instanceof ActiveMQObjectMessage) {
			ActiveMQObjectMessage activeMQObjectMessage =(ActiveMQObjectMessage)msg;
			Person person = (Person)activeMQObjectMessage.getObject();
			//从数据库查询
			List<Person> list = personDao.get(person.getName());
			solrUtil.addDoc(list);
		}
	}
}
