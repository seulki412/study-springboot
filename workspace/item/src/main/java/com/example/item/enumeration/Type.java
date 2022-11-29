package com.example.item.enumeration;

public enum Type {
	
	WALKING("워킹화"),
	RUNNING("런닝화"),
	TRACKING("트래킹화"),
	HIKING("등산화");
	
	final private String name; // 외부에서 접근하지 못하도록 잠궈둔다.
	
	private Type(String name) { //enum에서 생성자와 같은 개념
		this.name=name;
	}
	
	public String getName() {  //문자를 받아오는 함수
		return name;
	}
}
