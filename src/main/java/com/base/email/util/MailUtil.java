package com.base.email.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.base.email.dto.MailBean;

@Component
@PropertySource(value = {"classpath:/config/config.properties"})
public class MailUtil {
	@Value("${spring.mail.username}")
	private String MAIL_SENDER; //邮件发送者
	@Autowired
	private JavaMailSender javaMailSender;
	private Logger logger = LoggerFactory.getLogger(MailUtil.class);
	
	//发送文本邮件
	public  void sendSimpleMail(MailBean mailBean) {
        SimpleMailMessage mailMessage= new SimpleMailMessage();
        mailMessage.setFrom(MAIL_SENDER);
        mailMessage.setTo(mailBean.getRecipient());
        mailMessage.setSubject(mailBean.getSubject());
        mailMessage.setText(mailBean.getContent());
        //mailMessage.copyTo(copyTo);
        javaMailSender.send(mailMessage);
	}
	//HTML格式邮件发送
	public void sendHTMLMail(MailBean mailBean) {
		MimeMessage mimeMailMessage = null;
		try {
			mimeMailMessage = javaMailSender.createMimeMessage();
			// true 表示需要创建一个multipart message
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
			mimeMessageHelper.setFrom(MAIL_SENDER);
			mimeMessageHelper.setTo(mailBean.getRecipient());
			mimeMessageHelper.setSubject(mailBean.getSubject());
			// 邮件抄送
			// mimeMessageHelper.addCc("抄送人");
			mimeMessageHelper.setText(mailBean.getContent(), true);
			javaMailSender.send(mimeMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}
	//附件格式邮件发送
	public void sendAttachmentMail(MailBean mailBean) {
		MimeMessage mimeMailMessage = null;
		try {
			mimeMailMessage = javaMailSender.createMimeMessage();
			// true 表示需要创建一个multipart message
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
			mimeMessageHelper.setFrom(MAIL_SENDER);
			mimeMessageHelper.setTo(mailBean.getRecipient());
			mimeMessageHelper.setSubject(mailBean.getSubject());
			mimeMessageHelper.setText(mailBean.getContent());
			// 文件路径 目前写死在代码中，之后可以当参数传过来，或者在MailBean中添加属性absolutePath
			String absolutePath = "D:\\imgCut\\雪竹.jpg";
			FileSystemResource file = new FileSystemResource(new File(absolutePath));
			// FileSystemResource file = new FileSystemResource(new
			// File("src/main/resources/static/image/email.png"));
			String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator));
			// 添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
			mimeMessageHelper.addAttachment(fileName, file);
			// 多个附件
			// mimeMessageHelper.addAttachment(fileName1, file1);

			javaMailSender.send(mimeMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}
	//静态资源格式邮件发送
	public void sendInlineMail(MailBean mailBean) {
	    MimeMessage mimeMailMessage = null;
	    try {
	         mimeMailMessage = javaMailSender.createMimeMessage();
	         MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
	         mimeMessageHelper.setFrom(MAIL_SENDER);
	         mimeMessageHelper.setTo(mailBean.getRecipient());
	         mimeMessageHelper.setSubject(mailBean.getSubject());
	         mimeMessageHelper.setText(mailBean.getContent(), true);
	         //文件路径
	         String absolutePath = "D:\\imgCut\\雪竹.jpg";
	         FileSystemResource file = new FileSystemResource(new File(absolutePath));
	         //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"))
	         //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 
	         //mimeMessageHelper.addInline(rscId, res) 来实现
	         mimeMessageHelper.addInline("picture", file);
             
	         javaMailSender.send(mimeMailMessage);
	     } catch (Exception e) {
	         logger.error("邮件发送失败", e.getMessage());
	     }
	}
}
