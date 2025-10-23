package com.hk.board.utils;

import org.springframework.stereotype.Component;

@Component
public class Util {
	
	//문자열을 2자리로 변환해주는 메서드
	public String isTwo(String s) {
		return s.length()<2?"0"+s:s;
	}
}
