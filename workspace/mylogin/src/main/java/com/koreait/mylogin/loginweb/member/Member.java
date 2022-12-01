package com.koreait.mylogin.loginweb.member;

import lombok.Data;

@Data
public class Member {
	
	private Long id;			
	private String name;		// 로그인 ID
	private String loginId;		// 사용자 이름
	private String password;	// 사용자 pw
}
