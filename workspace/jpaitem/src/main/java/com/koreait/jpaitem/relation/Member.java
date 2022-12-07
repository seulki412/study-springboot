package com.koreait.jpaitem.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Member {

	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(name = "USERNAME")
	private String name;
	
//	@Column(name = "TEAM_ID")
//	private Long teamid;
	
	
	// @ManyToOne : 여기에선 Team이 하나
	// @JoinColumn(name = "TEAM_ID") : 관계 컬럼을 적어둔다. TEAM_ID와 조인해야한다. 네임값이 없으면 전역변수 명으로 매핑
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
}
