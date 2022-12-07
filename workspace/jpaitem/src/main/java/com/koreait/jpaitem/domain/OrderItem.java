package com.koreait.jpaitem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Setter @Getter
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	@Column(name = "ORDER_ID")
	private Long orderId;
	@Column(name = "ITEM_ID")
	private Long itemId;
	private int orderprice;
	private int count;
}
