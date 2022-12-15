package com.koreait.Springtest17;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentDTO {

	private Long id;
	private String studentName;
	private Integer age;
	private Integer subject;
	private String phone;
	private String addr;
	
	

	public StudentDTO() {
		super();
	}



	public StudentDTO(String studentName, Integer age, Integer subject, String phone, String addr) {
		super();
		this.studentName = studentName;
		this.age = age;
		this.subject = subject;
		this.phone = phone;
		this.addr = addr;
	}
	
	
	
	
	
	
	
}
