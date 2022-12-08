package com.koreait.jpaitem.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	
	@OneToMany(mappedBy = "member")
	private List<Order> order = new ArrayList<Order>();
	
	public void addMember(Order order) {
		order.setMember(this);
		this.order.add(order);
	}
}
