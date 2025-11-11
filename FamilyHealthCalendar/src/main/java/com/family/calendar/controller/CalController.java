package com.family.calendar.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.calendar.dtos.UserDto;
import com.family.calendar.dtos.VisitDto;
import com.family.calendar.service.CalService;
import com.family.calendar.utils.Util;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/calendar")
public class CalController {

    @Autowired
    private CalService calService;

    @Autowired
    private Util util;

    // ✅ 루트 접근 시 리다이렉트
    @GetMapping("/")
    public String home() {
        return "redirect:/calendar";
    }

    // ✅ 달력 메인 화면 (로그인 사용자 기준)
    @GetMapping("")
    public String calendar(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String month,
            Model model,
            HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        // 현재 날짜 기본값
        LocalDate now = LocalDate.now();

        // "null" 문자열 방어 및 기본값 설정
        int y = (year == null || year.equals("null") || year.isEmpty()) ? now.getYear() : Integer.parseInt(year);
        int m = (month == null || month.equals("null") || month.isEmpty()) ? now.getMonthValue() : Integer.parseInt(month);

        // ✅ 연월 보정
        if (m < 1) {
            m = 12;
            y -= 1;
        } else if (m > 12) {
            m = 1;
            y += 1;
        }

        // ✅ 날짜 계산 (월 첫째 날 요일, 마지막 날)
        LocalDate firstDay = LocalDate.of(y, m, 1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue(); // 월요일=1, 일요일=7
        if (dayOfWeek == 7) dayOfWeek = 0; // 일요일을 0으로 맞춤

        int lastDay = firstDay.lengthOfMonth();

        Map<String, Object> calMap = new HashMap<>();
        calMap.put("year", y);
        calMap.put("month", m);
        calMap.put("dayOfWeek", dayOfWeek + 1); // ✅ 요일 1부터 시작하도록 조정
        calMap.put("lastDay", lastDay);

        List<VisitDto> clist = calService.getVisitListByUser(user.getUser_id());

        model.addAttribute("calMap", calMap);
        model.addAttribute("clist", clist);

        return "calendar";
    }


    // ✅ AJAX: 특정 날짜의 방문 개수
    @GetMapping("/calcountajax")
    @ResponseBody
    public Map<String, Object> getVisitCountAjax(@RequestParam String yyyyMMdd) {
        Map<String, Object> result = new HashMap<>();
        result.put("count", 0);
        return result;
    }
}
