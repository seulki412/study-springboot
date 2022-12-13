package com.koreait.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Member;
import com.koreait.jpql.domain.Team;

import oracle.net.jdbc.TNSAddress.Address;

public class Main4 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			em.flush();
			em.clear();

//			List resultList =  em.createQuery("select  m.username, m,age from Member m").getResultList();
			
	         // 타입을 지정하지 못했기 때문에 object로 받아온다.
//	         Object o = resultList.get(0);
//	         Object[] result = (Object[])o;
//	         System.out.println("username = " + result[0]);
//	         System.out.println("userage = " + result[1]);
	         
//	         List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m").getResultList();
	         
//	         Object[] result = resultList.get(0);
//	         System.out.println("username = " + result[0]);
//	         System.out.println("userage = " + result[1]);

	       
	         List<MemberDTO> result = em.createQuery("select new com.koreait.jpql.MemberDTO( m.username, m.age) from Member m").getResultList();
	         
	         MemberDTO memberDTO = result.get(0);
	         System.out.println("username : " + memberDTO.getUsername());
	         System.out.println("age : " + memberDTO.getAge());
	         
			tx.commit(); 
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}