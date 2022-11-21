package com.koreait.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		System.out.println("controller 도착");
		model.addAttribute("data", "hello!!!!");
		return "hello";
	}
	
	//http://localhost:9090/hello-mvc?name=SpringMVC
	
//	@GetMapping("hello-mvc")
	public String hellomvc(@RequestParam("name")String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	/*
	 * @RequestParam
	 * - required : 파라미터 값 필수 여부, true -> 필수(default),false - 필수아님
	 * - defaultValue : 파라미터 값이 없을 경우 기본으로 들어갈 값
	 * */
	@GetMapping("hello-mvc")
	public String hellomvc2(@RequestParam(value="name", required = false, defaultValue="required test")String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
}
