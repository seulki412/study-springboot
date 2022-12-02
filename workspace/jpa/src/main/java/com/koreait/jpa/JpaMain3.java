package com.koreait.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {

	public static void main(String[] args) {
		//xml 파일 이름 hello -> 설정파일 읽어오기
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
				EntityManager em = emf.createEntityManager();
				
				// transaction 발생
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				
				try {
					
					Member2 member2 = new Member2();
//					member2.setId("ID_A");
					member2.setUsername("Hello");
					
					em.persist(member2);
					
					tx.commit();  // 쿼리는 이 시점에 날아간다.
				} catch (Exception e) {
					tx.rollback();
				}finally {
					em.close();
					emf.close();
				}
			}

	}

