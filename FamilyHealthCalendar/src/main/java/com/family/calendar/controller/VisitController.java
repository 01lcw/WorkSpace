package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.family.calendar.dtos.VisitDto;
import com.family.calendar.service.CommuService;
import com.family.calendar.service.VisitService;

@Controller
@RequestMapping("/visit")
public class VisitController {

	@Autowired
	private CommuService commuService; // ← 소문자로 시작해야 합니다

    @Autowired
    private VisitService service;
    
    @Autowired
    private VisitService visitService;

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("visits", visitService.getVisitList());
        return "calendar";
    }


    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer year,
                       @RequestParam(required = false) Integer month,
                       @RequestParam(required = false) Integer day,
                       Model model) {

        // 날짜 필터가 있을 경우 해당 날짜만 조회
        if (year != null && month != null && day != null) {
            String date = String.format("%04d-%02d-%02d", year, month, day);
            model.addAttribute("list", service.getVisitListByDate(date));
            model.addAttribute("selectedDate", date); // 화면 표시용
        } else {
            model.addAttribute("list", service.getVisitList());
        }

        return "visitList";
    }


    @GetMapping("/insertForm")
    public String insertForm() {
        return "visitInsertForm";
    }

    @PostMapping("/insert")
    public String insert(VisitDto dto) {
        service.insertVisit(dto);
        return "redirect:/visit/list";
    }


    @PostMapping("/update")
    public String update(VisitDto dto) {
        service.updateVisit(dto);
        return "redirect:/visit/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long visit_id) {
        service.deleteVisit(visit_id);
        return "redirect:/visit/list";
    }
    
    @GetMapping("/detail")
    public String detail(@RequestParam Long visit_id, Model model) {
        model.addAttribute("dto", service.getVisitDetail(visit_id));
        model.addAttribute("logList", commuService.getLogsByVisit(visit_id)); // 형변환 필요 없음
        return "visitDetail";
    }
}
