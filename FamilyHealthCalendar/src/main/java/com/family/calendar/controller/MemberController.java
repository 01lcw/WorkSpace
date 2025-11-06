package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.family.calendar.dtos.UserDto;
import com.family.calendar.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/loginProc")
    public String loginProc(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        UserDto user = memberService.login(username, password);

        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/calendar";  // ✅ 성공 시 달력으로
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";               // ✅ redirect 금지, 그냥 "login"
        }
    }



    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(UserDto dto) {
        memberService.register(dto);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
