package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.family.calendar.dtos.VisitDto;
import com.family.calendar.service.VisitService;
import com.family.calendar.service.FamilyMemberService;
import java.time.LocalDate;
import jakarta.servlet.http.HttpSession;
import com.family.calendar.dtos.UserDto;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private FamilyMemberService memberService;

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
    public String insertForm(@RequestParam(required = false) Integer year,
                             @RequestParam(required = false) Integer month,
                             @RequestParam(required = false) Integer day,
                             Model model, HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("members", memberService.getMembersByUser(user.getUser_id()));
        if (year != null && month != null && day != null) {
            model.addAttribute("selectedDate",
                    String.format("%04d-%02d-%02d", year, month, day));
        }
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
    public String detail(@RequestParam("visit_id") int visit_id, Model model) {
        model.addAttribute("dto", visitService.getVisitDetail(visit_id));
        return "VisitDetail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("visit_id") int visit_id) {
        visitService.deleteVisit(visit_id);
        return "redirect:/visit/list";
    }
}
