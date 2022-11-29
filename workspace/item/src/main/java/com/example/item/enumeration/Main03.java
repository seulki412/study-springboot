package com.example.item.enumeration;

public class Main03 {

	public static void main(String[] args) {

		for(Type type : Type.values()) {
			System.out.println(type.getName());
		}
	}

}
