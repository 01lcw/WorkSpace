package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.family.calendar.dtos.FamilyMemberDto;
import com.family.calendar.dtos.UserDto;
import com.family.calendar.service.FamilyMemberService;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/family")
public class FamilyMemberController {

    @Autowired
    private FamilyMemberService memberService;

    // ✅ 가족 구성원 목록
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        List<FamilyMemberDto> list = memberService.getMembersByUser(user.getUser_id());
        model.addAttribute("members", list);
        return "familyList";
    }

    // ✅ 추가 폼
    @GetMapping("/insertForm")
    public String insertForm() {
        return "familyInsertForm";
    }

    // ✅ 추가 처리
 // ✅ 추가 처리
    @PostMapping("/insert")
    public String insert(FamilyMemberDto dto, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        memberService.insertMember(dto, user.getUser_id()); // ✅ 올바른 변수명
        return "redirect:/family/list";
    }




    // ✅ 수정 폼
    @GetMapping("/updateForm")
    public String updateForm(@RequestParam Long member_id, Model model, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        FamilyMemberDto dto = memberService.getMemberDetail(member_id);
        if (dto == null) return "redirect:/family/list";

        model.addAttribute("dto", dto);
        return "familyUpdateForm";
    }

    // ✅ 수정 처리
    @PostMapping("/update")
    public String update(FamilyMemberDto dto, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        dto.setUser_id(user.getUser_id());
        memberService.updateMember(dto);
        return "redirect:/family/list";
    }

    // ✅ 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam Long member_id, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) return "redirect:/login";

        memberService.deleteMember(member_id);
        return "redirect:/family/list";
    }
}
