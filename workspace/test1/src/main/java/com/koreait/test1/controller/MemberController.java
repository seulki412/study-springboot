package com.koreait.test1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.test1.member.Member;
import com.koreait.test1.member.MemberFormDTO;
import com.koreait.test1.service.MemberService;

@Controller
public class MemberController {

	// Controller가 Service를 의존한다라고 표현
	// Service는 여러 Controller에서 가져다 쓸 수 있기 때문에
	// Spring Container에 등록을 해야한다.
//	MemberService mService = new MemberService();
	
	// 스프링스럽게 작업하기
	// Service는 Spring Container에 하나만 생성 및 등록해서 같이 공유해서 쓸 수 있다.
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/members/new")
	public String create(MemberFormDTO form) {
		Member member = new Member();
		member.setName(form.getName());
		memberService.join(member);
		
		//홈 화면으로 돌린다.
		return "redirect:/";
		// redirect로 보내면 viewResolver가 아니라 url매핑을 다시 찾는다.
		// viewResolver를 들리면 model에 데이터를 가지고 간다.
		
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
		
	}
}
