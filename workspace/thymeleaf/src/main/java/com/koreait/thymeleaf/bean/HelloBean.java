package com.koreait.thymeleaf.bean;

import org.springframework.stereotype.Component;

@Component("helloBean")
// = HelloBean hb = new HelloBean();
public class HelloBean {

	public String hello(String data) {
		return "Hello" + data;
	}
	
	
}
