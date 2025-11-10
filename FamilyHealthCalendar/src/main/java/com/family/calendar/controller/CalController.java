package com.family.calendar.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.family.calendar.dtos.VisitDto;
import com.family.calendar.service.VisitService;
import com.family.calendar.utils.Util;

@Controller
public class CalController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private Util util;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/calendar";
    }


    @GetMapping("/calendar")
    public String calendar(@RequestParam(required = false) Integer year,
                           @RequestParam(required = false) Integer month,
                           Model model) {

        LocalDate now = LocalDate.now();
        int y = (year == null) ? now.getYear() : year;
        int m = (month == null) ? now.getMonthValue() : month;

        Map<String, Integer> calMap = new HashMap<>();
        calMap.put("year", y);
        calMap.put("month", m);
        calMap.put("lastDay", util.getLastDay(y, m));
        calMap.put("dayOfWeek", LocalDate.of(y, m, 1).getDayOfWeek().getValue());

        List<VisitDto> clist = visitService.getVisitListByUser(1); // ✅ 임시 user_id (로그인 세션 붙이기 전)

        model.addAttribute("calMap", calMap);
        model.addAttribute("clist", clist);
        return "calendar";
    }

    // AJAX: 특정 날짜의 방문 개수
    @GetMapping("/calcountajax")
    @ResponseBody
    public Map<String, Object> getVisitCountAjax(@RequestParam String yyyyMMdd) {
        Map<String, Object> result = new HashMap<>();
        result.put("count", 0);
        return result;
    }
}
