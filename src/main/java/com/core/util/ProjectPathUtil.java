package com.core.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class ProjectPathUtil {

	@Autowired
	private HttpServletRequest request;
	
	//获取项目绝对路径
	public String getProjectPath() {
		return this.getClass().getResource("/").getPath();
	}
	
	//获取web-inf路径：request.getServletContext().getRealPath("/")
	public String getRealPath() {
		return request.getServletContext().getRealPath("/");
	}
	
}
