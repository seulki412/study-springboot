package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.jpashop.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	@Autowired
	private final EntityManager em;
	
	public void save(Item item) {
		em.persist(item);
	}
	
	public List<Item> findAllItems(){
		 return em.createQuery("select i from Item i", Item.class).getResultList();
	}
}
