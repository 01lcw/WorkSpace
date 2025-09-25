package com.hk.board.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드 초기화 생성자
// @RequiredArgsConstructor // 원하는 필드만 초기화 생성자 
//getter/setter
@Data
@ToString

public class AnsDto {
	
	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regDate;
	private int refer;
	private int step;
	private int depth;
	private String readCount;
	private String delfag;
	
}