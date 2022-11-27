package com.koreait.test1.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.koreait.test1.member.Member;

@Repository
public class JpaMemberRepository implements MemberRepository{
	
	// JPA는 EntityManager로 뭐든지 동작 한다.
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
		// select m from Member m : JPQL 이라는 쿼리
		
	}

}
