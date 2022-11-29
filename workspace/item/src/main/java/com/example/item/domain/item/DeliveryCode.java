package com.example.item.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 변수 모두를 파라미터로 받는 생성자 
public class DeliveryCode {

	private String code;
	private String displayName;
}
