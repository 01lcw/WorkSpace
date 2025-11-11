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
    public String calendar(Model model,
                           HttpSession session,
                           @RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month) {

        // 세션에서 로그인 사용자 가져오기
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        int user_id = loginUser.getUser_id();

        // ---------------- 달력 계산 ----------------
        LocalDate now = LocalDate.now();
        int y = (year == null) ? now.getYear() : year;
        int m = (month == null) ? now.getMonthValue() : month;

        Map<String, Integer> calMap = new HashMap<>();
        calMap.put("year", y);
        calMap.put("month", m);
        calMap.put("lastDay", util.getLastDay(y, m));
        calMap.put("dayOfWeek", LocalDate.of(y, m, 1).getDayOfWeek().getValue());

        // ✅ 로그인한 유저의 가족 방문 기록만 조회
        List<VisitDto> clist = calService.getVisitListByUser(user_id);

        model.addAttribute("clist", clist);
        model.addAttribute("calMap", calMap);

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
