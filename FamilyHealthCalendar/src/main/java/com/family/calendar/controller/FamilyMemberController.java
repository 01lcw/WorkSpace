package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.family.calendar.dtos.FamilyMemberDto;
import com.family.calendar.dtos.UserDto;
import com.family.calendar.service.FamilyMemberService;

@Controller
@RequestMapping("/family")
public class FamilyMemberController {

    @Autowired
    private FamilyMemberService service;

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("members", service.getMembersByUser(user.getUser_id()));
        return "familyList";
    }

    @GetMapping("/insertForm")
    public String insertForm() {
        return "familyInsertForm";
    }

    @PostMapping("/insert")
    public String insert(FamilyMemberDto dto, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";
        dto.setUser_id(user.getUser_id());
        service.insertMember(dto);
        return "redirect:/family/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("member_id") Long id, Model model) {
        model.addAttribute("dto", service.getMemberDetail(id));
        return "familyUpdateForm";
    }

    @PostMapping("/update")
    public String update(FamilyMemberDto dto) {
        service.updateMember(dto);
        return "redirect:/family/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("member_id") Long id) {
        service.deleteMember(id);
        return "redirect:/family/list";
    }
}
