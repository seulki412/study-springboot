package com.koreait.jpaitem;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpaitem.embedded.Address;
import com.koreait.jpaitem.embedded.Member;
import com.koreait.jpaitem.embedded.Period;



public class JpaMain5 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Address addr = new Address("서울", "역삼", "123");
			
			Member member = new Member();
			member.setUsername("user1");
			member.setAddress(addr);
			em.persist(member);
			
					
			Member member2 = new Member();
			member2.setUsername("user2");
			// user1과 user2가 같은 addr을 가지고 있다.
			member2.setAddress(addr);
			em.persist(member2);
			
			// user1의 주소만 newCity로 변경하고 싶다.
			// 생성자로만 셋팅할수 있고, 게터로만 가져오게 막는다. 세터는 생성하지 않고 세터로 수정하는 것을 막아야 한다.
			Address newAddr = new Address("newCity", "역삼", "123");
			member.setAddress(newAddr);
			em.persist(member);

			
			tx.commit(); 
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}
