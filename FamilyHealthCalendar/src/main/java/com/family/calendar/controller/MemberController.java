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
    private MemberService service;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/loginProc")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        UserDto user = service.login(username, password);
        if (user == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
        session.setAttribute("loginUser", user);
        return "redirect:/calendar";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String join(UserDto dto) {
        service.register(dto);
        return "redirect:/login";
    }
}
