package com.koreait.core2.controller.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

	/*
	 * ModelAndView
	 * - Controller 처리 결과 후 응답할 view와 view에 전달할 값을 저장
	 * */
	@RequestMapping("/response-view-v1")
	public ModelAndView responseViewV1() {
		ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
		return mav;
	}
	
	
	/*
	 * Controller에서 return 이 String이면 view의 논리적인 이름이 된다.
	 * @ResponseBody를 넣지 않도록 주의
	 * */
//	@ResponseBody
	@RequestMapping("/response-view-v2")
	public String responseViewV2(Model model) {
		model.addAttribute("data", "model data");
		return "response/hello";
	}
}
