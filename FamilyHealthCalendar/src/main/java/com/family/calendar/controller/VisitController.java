package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.family.calendar.dtos.VisitDto;
import com.family.calendar.service.VisitService;
import com.family.calendar.service.FamilyMemberService;
import java.time.LocalDate;
import java.util.List;
import com.family.calendar.dtos.HealthLogDto;
import com.family.calendar.service.HealthLogService;

import jakarta.servlet.http.HttpSession;
import com.family.calendar.dtos.UserDto;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private FamilyMemberService memberService;
    
    @Autowired
    private HealthLogService healthLogService;


    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer year,
                       @RequestParam(required = false) Integer month,
                       @RequestParam(required = false) Integer day,
                       Model model, HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("list", visitService.getVisitListByUser(user.getUser_id()));

        if (year != null && month != null && day != null) {
            model.addAttribute("selectedDate",
                    String.format("%04d-%02d-%02d", year, month, day));
        }
        return "VisitList";
    }

    @GetMapping("/insertForm")
    public String insertForm(@RequestParam(required = false) String year,
                             @RequestParam(required = false) String month,
                             @RequestParam(required = false) String day,
                             Model model, HttpSession session) {

        // ✅ 로그인 세션 확인
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        // ✅ 로그인 사용자 가족 구성원 목록
        model.addAttribute("members", memberService.getMembersByUser(user.getUser_id()));

        // ✅ 날짜 파라미터 처리 (null, "null", 빈 문자열 모두 방어)
        LocalDate now = LocalDate.now();
        int y = (year == null || year.equals("null") || year.isEmpty()) ? now.getYear() : Integer.parseInt(year);
        int m = (month == null || month.equals("null") || month.isEmpty()) ? now.getMonthValue() : Integer.parseInt(month);
        int d = (day == null || day.equals("null") || day.isEmpty()) ? now.getDayOfMonth() : Integer.parseInt(day);

        // ✅ yyyy-MM-dd 형식으로 뷰에 전달
        model.addAttribute("selectedDate", String.format("%04d-%02d-%02d", y, m, d));

        return "VisitInsertForm";
    }


    @PostMapping("/insert")
    public String insert(VisitDto dto, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        dto.setUser_id(user.getUser_id());
        visitService.insertVisit(dto);
        return "redirect:/visit/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int visit_id, Model model) {
        VisitDto dto = visitService.getVisitDetail(visit_id);
        model.addAttribute("dto", dto);

        // ✅ 건강기록 목록 추가
        List<HealthLogDto> logList = healthLogService.getLogsByVisit(visit_id);
        model.addAttribute("logList", logList);

        return "visitDetail";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("visit_id") int visit_id) {
        visitService.deleteVisit(visit_id);
        return "redirect:/visit/list";
    }
}