package com.core.solr.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import com.base.databackup.dto.Person;

@Component
public class SolrUtil {
	
	@Autowired
	private SolrClient solr;
	
	// 向索引库中填数据
	public void addDoc(Person person) {
		//solr.addBean(person);
		SolrInputDocument document = new SolrInputDocument();
		// 往doc中添加字段,在客户端这边添加的字段必须在服务端中有过定义
		document.addField("id", person.getId());
		document.addField("name", person.getName());
		document.addField("password", person.getPassword());
		document.addField("age", person.getAge());
		document.addField("address", person.getAddress());
		document.addField("title", person.getTitle());
		// 获得一个solr服务端的请求，去提交 ,选择具体的某一个solr core
		try {
			solr.add(document);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//插入list
	public void addDoc(List<Person> list) {
		Collection<SolrInputDocument> docs = new ArrayList<>();
		SolrInputDocument document;
		for (Person person : list) {
			document = new SolrInputDocument();
			document.addField("id", person.getId());
			document.addField("name", person.getName());
			document.addField("password", person.getPassword());
			document.addField("age", person.getAge());
			document.addField("address", person.getAddress());
			document.addField("title", person.getTitle());
			docs.add(document);
		}
		// 获得一个solr服务端的请求，去提交 ,选择具体的某一个solr core
		try {
			solr.add(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除
	public void delDocByID(Person person) {
		try {
			solr.deleteById(person.getId());
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	//删除所有
	public void delAllDoc() {
		try {
			solr.deleteByQuery("*:*");
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}finally {
			try {
				solr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//查询
	public SolrDocumentList query(Person person) throws SolrServerException, IOException {
		//下面设置solr查询参数
        //query.set("q", "*:*");// 参数q  查询所有   
        //query.set("q","周星驰");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来 ，这个作用适用于联想查询
 
        //参数fq, 给query增加过滤查询条件  
        //query.addFilterQuery("id:[0 TO 9]");//id为0-4  
 
        //给query增加布尔过滤条件  
        //query.addFilterQuery("description:演员");  //description字段中含有“演员”两字的数据
 
        //参数df,给query设置默认搜索域  
        //query.set("df", "name");  
 
        //参数sort,设置返回结果的排序规则  
        //query.setSort("id",SolrQuery.ORDER.desc);
		
		SolrQuery query = new SolrQuery(); 
		query.set("q", "name:"+person.getName());
		//设置分页参数  
        query.setStart(0);  
        query.setRows(10);//每一页多少值  
 
        //参数hl,设置高亮  
        query.setHighlight(true);  
        //设置高亮的字段  
        query.addHighlightField("name");  
        //设置高亮的样式  
        query.setHighlightSimplePre("<font color='red'>");  
        query.setHighlightSimplePost("</font>"); 
 
        //获取查询结果
        QueryResponse response = solr.query(query);  
        //两种结果获取：得到文档集合或者实体对象
 
        //查询得到文档的集合  
        SolrDocumentList solrDocumentList = response.getResults();  
		return solrDocumentList;
	}

}
