package com.koreait.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!");
		return "hello";
	}
	
	// "/" -> index.htjml 보다 우선순위 높음
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
