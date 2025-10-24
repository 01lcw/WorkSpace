package com.hk.board.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class Util {
	
	//문자열을 2자리로 변환해주는 메서드
	public String isTwo(String s) {
		return s.length()<2?"0"+s:s;
	}
	
	//mdate: 날짜형식으로 변환
	public String toDates(String mdate) {
		//Date 형식으로 만들기: "yyyy-MM-dd hh:mm:ss"
		//mdate 12자리 -> "yyy-MM-dd hh:mm:ss"로 변환
		String m=mdate.substring(0,4)+"-"+mdate.substring(4,6)+"-"+mdate.substring(6,8)+" "+mdate.substring(8,10)+":"+mdate.substring(10)+":00";
		
		Timestamp tm =Timestamp.valueOf(m);//String -> Date 변환
		
		//java에 SimpleDateFormat 객체
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy년MM월dd일 HH:mm"	);
		return sdf.format(tm);
	}
}
