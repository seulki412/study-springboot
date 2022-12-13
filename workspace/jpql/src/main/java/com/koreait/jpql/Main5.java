package com.koreait.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.koreait.jpql.domain.Member;
import com.koreait.jpql.domain.Team;

import oracle.net.jdbc.TNSAddress.Address;

public class Main5 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
						
				Member member = new Member();
				member.setUsername("member" );
				member.setAge(10);
				em.persist(member);
			
			
			em.flush();
			em.clear();

//			// 조인
//			String sql = "select m from Member m inner join m.team t where t.name = :teamName";
//			List<Member> resultList = em.createQuery(sql, Member.class).getResultList();

			// left outer join
//			String sql = "select m from Member m left outer join m.team t";
//			List<Member> resultList = em.createQuery(sql, Member.class).getResultList();
			
			String sql = "select m from Member m where m.age > (select avg(m2.age) from Member m2)";
			List<Member> resultList = em.createQuery(sql, Member.class).getResultList();
			tx.commit(); 
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}