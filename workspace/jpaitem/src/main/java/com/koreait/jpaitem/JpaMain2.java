package com.koreait.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.relation.Member;
import com.koreait.jpaitem.relation.Team;

public class JpaMain2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// 양방향 매핑 시 가장 많이 하는 실수
//			Member member = new Member();
//			member.setName("member1");
//			em.persist(member);
//	        
//	        Team team = new Team();
//			team.setName("TeamA");
//			team.getMember().add(member);
//			em.persist(team); 
			
	        
	        Team team = new Team();
			team.setName("TeamA");
			em.persist(team); 
			
			// 오너가 인서트 해야한다.
			Member member = new Member();
			member.setName("member1");
//			member.setTeam(team);
//			member.changeTeam(team);
			em.persist(member);
			
			team.addMember(member);
	        
//	        em.flush();
//	        em.clear();
	        
			// 객체 지향적인 입장에서 양쪽에 모두 값을 넣어주어야 한다.
			// 양방향 매핑시에는 양쪽에 값을 모두 입력해 주어야 한다.
			// DB를 다시 다녀오지 않고, 객체 상태로만 사용할 수 있다.
			// 멤버에도 팀을 넣어주고, 팀에도 멤버를 넣어주어야 한다.
//			team.getMember().add(member);
			
	        System.out.println("=============================");

	        // 팀으로 멤버를 읽어오는것만 가능하다.
	        Team findTeam = em.find(Team.class, team.getId());
	        List<Member> members = findTeam.getMember();
	        
	        for(Member m : members) {
	        	System.out.println("m = " + m.getName());
	        }
	        
	        
	        
	        System.out.println("=============================");

	       
			tx.commit(); 
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}
