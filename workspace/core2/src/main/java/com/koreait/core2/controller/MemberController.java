package com.koreait.core2.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.core2.member.Member;
import com.koreait.core2.member.MemberFormDTO;
import com.koreait.core2.service.MemberService;

@Controller
public class MemberController {

	// Controller가 Service를 의존한다라고 표현
	// Serivce는 여러 Controller에서 가져다 쓸 수 있기 때문에
	// Spring Container에 등록을 해야한다.
//	MemberService mService = new MemberService();
	
	// 스프링 스럽게 작업하기
	// service는 Spring Container에 하나만 생성 및 등록 해서 같이 공유해서 쓸 수 있다.
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	
	/* 
	 * 필드 주입(Field Injection)
	 *  - final 키워드를 사용할 수 없어, 순환 참조가 발생할 수 있다.권장하지 않는다.
	 */
	//@Autowired private final MemberService memberService;
	
	
	/*
	 * Setter Injection(수정자 주입)
	 *  - public 으로 노출이 되기 때문에 다른곳에서 주입 가능하다. 
	 */
//	private MemberService memberService;
//	
//	@Autowired
//	public void setMember(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	@GetMapping(value="/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping(value="members/new")
	public String create(MemberFormDTO form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		
		//홈 화면으로 돌린다.
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	

	
	
	
}