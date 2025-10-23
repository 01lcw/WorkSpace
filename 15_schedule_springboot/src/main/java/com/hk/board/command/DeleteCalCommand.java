package com.hk.board.command;

public class DeleteCalCommand {
	
	//seq의 값이 null값이면 하나 이상 체크하지 않고 삭제 요청을 함
	//에러 메시지: "삭제하려면 최소 하나 이상 체크하세요"
	private String[] seq;
}
