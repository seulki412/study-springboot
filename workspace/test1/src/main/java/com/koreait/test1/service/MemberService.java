package com.koreait.test1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.test1.member.Member;
import com.koreait.test1.repository.MemberRepository;


@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	//회원가입
	public int join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	//전체회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
}
