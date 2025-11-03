package com.family.calendar.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

//@Controller가 적용된 클래스에서 발생되는 예외를 catch함
@Controller
@Slf4j
public class Exceptionhandle {
	//실행중에 Exception이 발생하면 메서드 실행
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		log.error("Exeption발생:{}", e.getMessage());
		model.addAttribute("msg","오류가 발생하여 확인하세요");
		return "error";
	}
}
