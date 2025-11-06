package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.calendar.dtos.UserDto;
import com.family.calendar.service.VisitService;
import com.family.calendar.utils.Util;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CalController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private Util util; // 기존 CalBoard에서 쓰던 유틸
    
    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "redirect:/login";  // ✅ 로그인 페이지로 이동
        }
    }


    @GetMapping("/calendar")
    public String calendar(@RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month,
                           Model model, HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        java.util.Calendar cal = java.util.Calendar.getInstance();
        int nowYear = (year == null) ? cal.get(java.util.Calendar.YEAR) : year;
        int nowMonth = (month == null) ? cal.get(java.util.Calendar.MONTH) + 1 : month;

        cal.set(nowYear, nowMonth - 1, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int lastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        Map<String, Object> calMap = new HashMap<>();
        calMap.put("year", nowYear);
        calMap.put("month", nowMonth);
        calMap.put("dayOfWeek", dayOfWeek);
        calMap.put("lastDay", lastDay);

        // ✅ 로그인한 사용자의 가족 방문 일정만
        model.addAttribute("clist", visitService.getVisitListByUser(user.getUser_id()));
        model.addAttribute("calMap", calMap);

        return "calendar";
    }

    
    @GetMapping("/calcountajax")
    @ResponseBody
    public Map<String, Object> getVisitCountAjax(@RequestParam String yyyyMMdd,
                                                 HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        if (loginUser == null) {
            return Map.of("count", 0);
        }

        Long userId = loginUser.getUser_id();
        int count = visitService.getVisitCountByDateAndUser(yyyyMMdd, userId);

        return Map.of("count", count);
    }

}
