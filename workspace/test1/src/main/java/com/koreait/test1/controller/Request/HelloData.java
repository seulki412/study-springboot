package com.koreait.test1.controller.Request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter  @ToString @RequiredArgsConstructor 
public class HelloData {

	private String username;
	private int age;
}
