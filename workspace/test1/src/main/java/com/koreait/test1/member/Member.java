package com.koreait.test1.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "mySequence")
	@SequenceGenerator(name = "mySequence", sequenceName = "member_seq", allocationSize = 1)
	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
