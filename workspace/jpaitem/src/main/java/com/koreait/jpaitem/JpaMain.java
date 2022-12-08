package com.koreait.jpaitem;

import java.util.List;

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
////	        member.setTeamid(team.getId()); // team이 영속성컨텍스트에 있기때문에 디비select가 아니라 get으로 가져오면 된다.
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
			
			// -> persist하면 영속상태가 된다.
			// 영속상태가 될 때, PK의 값이 세팅이 된 후에 영속상태가 된다.
			// Team을 먼저 생성해서 PK를 생성해야 FK를 생성할 수 있다.
			em.persist(team); 
			
			Member member = new Member();
			member.setName("member1");
			// FK인 Member 에서 PK 객체인 Team을 통째로 가져온다. 
//			member.setTeam(team);
	        em.persist(member);
	        
	        
	        //강제 db 쿼리를 보고 싶을 때
	        // 영속성 컨텍스트를 비워준다.
	        em.flush();
	        em.clear();
	        
	        //select
	        // find시에 1차캐시에서 가지고 와서 select문이 없다.
	        Member findmember = em.find(Member.class, member.getId());
	        // Team 객체를 통째로 가져온다.
	        Team findTeam = findmember.getTeam();
	        System.out.println("findTeam : " + findTeam.getName());
	        
	                
	        // 수정
//	        Team  newTeam = em.find(Team.class, 1L);
//	        findmember.setTeam(newTeam);
//	        System.out.println("findTeamName : " + newTeam.getName());
//	        System.out.println("findTeam.getId() : " + newTeam.getId());
	        
	        
	        // 양방향 매핑
	        Member findSideMember = em.find(Member.class, member.getId());
	        List<Member> members = findmember.getTeam().getMember();
	        
	        for(Member m : members) {
	        	System.out.println("result1 = " + m.getName());
	        }
	        
	        
			tx.commit(); // commit하면 영속성 컨텍스트에 들어가기때문에 여기서 테이블 생성
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}
