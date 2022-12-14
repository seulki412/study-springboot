package com.koreait.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	private LocalDateTime orderDate;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderitems = new ArrayList<OrderItem>();
	
	// 주문상태(ORDER, CANCEL) -> Enum
	// orderstatus에는 enum에 정의된 값만 들어갈 수 있다.
	@Enumerated(EnumType.STRING)
	private OrderStatus orderstatus;
	
	// 연관관계 메서드
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void setOrderItem(OrderItem orderItem) {
		orderitems.add(orderItem);
		orderItem.setOrder(this);
	}
}
