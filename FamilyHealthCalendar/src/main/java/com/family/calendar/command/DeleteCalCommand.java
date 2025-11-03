package com.family.calendar.command;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//command역할: client와 controller간에 데이터 주고받는 중간 역할
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeleteCalCommand {
	
	//seq의 값이 null값이면 하나 이상 체크하지 않고 삭제 요청을 함
	//에러 메시지: "삭제하려면 최소 하나 이상 체크하세요"
	@NotEmpty(message = "삭제하려면 최소 하나이상 체크하세요")
	private String[] seq;
}
