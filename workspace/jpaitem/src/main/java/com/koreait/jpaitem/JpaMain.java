package com.koreait.jpaitem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.relation.Member;
import com.koreait.jpaitem.relation.Team;

public class JpaMain {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
//			Team team = new Team();
//			team.setName("TeamA");
//			
//			// -> 영속상태가 되면, PK의 값이 세팅이 된 후 영속상태가 된다.
//			// Team을 먼저 생성해서 PK 셋팅 가능
//			em.persist(team); 
//			
//			Member member = new Member();
//			member.setName("member1");
////	        member.setTeamid(team.getId());
//			member.setTeam(team);
//	        em.persist(member);
//	        
//	        //select
//	        // 어느팀 소속인지 알고 싶을 때 jpa or db에게 셰속 물어봐야한다.
//	        Member findmember = em.find(Member.class, member.getId());
//	        Long findTeamid = findmember.getTeamid();
//	        Team findTeam = em.find(Team.class, findTeamid);
//	        System.out.println("findTeam : " + findTeam.getName());
			
	        
	        
	        Team team = new Team();
			team.setName("TeamA");
			
			// -> 영속상태가 되면, PK의 값이 세팅이 된 후 영속상태가 된다.
			// Team을 먼저 생성해서 PK 셋팅 가능
			em.persist(team); 
			
			Member member = new Member();
			member.setName("member1");
			member.setTeam(team);
	        em.persist(member);
	        
	        
	        //강제 db 쿼리를 보고 싶을 때
	        em.flush();
	        em.clear();
	        
	        //select
	        // find시에 1차캐시에서 가지고와서 select문이 없다.
	        Member findmember = em.find(Member.class, member.getId());
	        Team findTeam = findmember.getTeam();
	        System.out.println("findTeam : " + findTeam.getName());
	        
	        
	        
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}

}
