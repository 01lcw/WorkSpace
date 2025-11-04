package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.family.calendar.service.VisitService;
import com.family.calendar.utils.Util;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CalController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private Util util; // 기존 CalBoard에서 쓰던 유틸

    @GetMapping("/calendar")
    public String calendar(@RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month,
                           Model model) {

        // 기본 년/월 세팅
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int nowYear = (year == null) ? cal.get(java.util.Calendar.YEAR) : year;
        int nowMonth = (month == null) ? cal.get(java.util.Calendar.MONTH) + 1 : month;

        // 마지막 날짜, 시작 요일 계산
        cal.set(nowYear, nowMonth - 1, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int lastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        // 캘린더 정보 맵 구성
        Map<String, Object> calMap = new HashMap<>();
        calMap.put("year", nowYear);
        calMap.put("month", nowMonth);
        calMap.put("dayOfWeek", dayOfWeek);
        calMap.put("lastDay", lastDay);

        // 방문 일정 리스트 불러오기
        model.addAttribute("clist", visitService.getVisitList());
        model.addAttribute("calMap", calMap);

        return "calendar"; // templates/calboard/calendar.html
    }
}
