package com.base.email.web;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.base.email.dto.MailBean;
import com.base.email.util.MailUtil;

@RestController
public class EmailController {

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private TemplateEngine templateEngine;

	//接收人
	private static final String RECIPINET = "15811468690@163.com";
	//发送文本
	@RequestMapping("sendSimpleMail")
	public void sendSimpleMail() {
		MailBean mailBean = new MailBean();
		mailBean.setRecipient(RECIPINET);
		mailBean.setSubject("SpringBootMail之这是一封文本的邮件");
		mailBean.setContent("邮件");
		mailUtil.sendSimpleMail(mailBean);
	}
	//发送HTML
	@RequestMapping("sendHTMLMail")
	public void sendHTMLMail() {
		MailBean mailBean = new MailBean();
	       mailBean.setRecipient(RECIPINET);
	       mailBean.setSubject("SpringBootMailHTML之这是一封HTML格式的邮件");
	       StringBuilder sb = new StringBuilder();
	       sb.append("<h2>SpirngBoot测试邮件HTML</h2>")
	         .append("<p style='text-align:left'>这是一封HTML邮件...</p>")
	         .append("<p> 时间为</p>")
	         .append("<button>按钮</button>");
	       mailBean.setContent(sb.toString());
	       mailUtil.sendHTMLMail(mailBean);
	}

	// 附件格式邮件发送
	@RequestMapping("sendAttachmentMail")
	public void sendAttachmentMail() {
		MailBean mailBean = new MailBean();
		mailBean.setRecipient(RECIPINET);
		mailBean.setSubject("SpringBootMail之这是一封有附件格式的邮件");
		mailBean.setContent("SpringBootMail发送一封有附件格式的邮件");
		mailUtil.sendAttachmentMail(mailBean);
	}
	//静态资源格式邮件发送
	@RequestMapping("sendInlineMail")
	public void sendInlineMail() {
		MailBean mailBean = new MailBean();
		// id,目前写死了，可根据需要封装
		String rscId = "picture";
		String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
		mailBean.setRecipient(RECIPINET);
		mailBean.setSubject("SpringBootMail之这是一封有静态资源格式的邮件");
		mailBean.setContent(content);
		mailUtil.sendInlineMail(mailBean);
	}
	//模板发送
	@RequestMapping("sendTemplate2Mail")
	public void sendTemplate2Mail() {
		// 注意：Context 类是在org.thymeleaf.context.Context包下的。
		Context context = new Context();
		// html中填充动态属性值
		context.setVariable("username", "码农用户");
		context.setVariable("url", "https://www.aliyun.com/?utm_content=se_1000301881");
		// 注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
		// org.thymeleaf.exceptions.TemplateInputException: Error resolving template
		// [email]
		String emailContent = templateEngine.process("/base/module/email/index", context);

		MailBean mailBean = new MailBean();
		mailBean.setRecipient(RECIPINET);
		mailBean.setSubject("主题：这是模板邮件");
		mailBean.setContent(emailContent);
		mailUtil.sendHTMLMail(mailBean);
	}
}
