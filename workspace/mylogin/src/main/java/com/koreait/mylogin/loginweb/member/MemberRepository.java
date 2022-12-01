package com.koreait.mylogin.loginweb.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

	private static Map<Long, Member> store = new HashMap<Long, Member>();
	private static Long sequence = 0L;
	
	public Member save(Member member) { // 저장
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id) { // 아이디 찾기
		return store.get(id); // 스토어에 저장된 id값 찾아서 바로 리턴
	}
	
	public List<Member> findAll(){ // 전체 회원 조회
		return new ArrayList<Member>(store.values());
	}
	
	public Member findByLoginId(String loginId) {
		List<Member> all = findAll();
		for(Member m : all) {
			if(m.getLoginId().equals(loginId)) {
				return m;
			}
		}
		return null;
	}
}
