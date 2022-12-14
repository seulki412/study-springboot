package com.koreait.jpashop.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

// 화면 상에서만 사용한다.
@Getter @Setter
public class MemberForm {

	@NotEmpty(message = "회원 이름은 필수입니다.")
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
}
