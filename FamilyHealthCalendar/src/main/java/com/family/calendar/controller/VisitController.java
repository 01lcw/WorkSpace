package com.family.calendar.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.family.calendar.dtos.UserDto;
import com.family.calendar.dtos.VisitDto;
import com.family.calendar.service.CommuService;
import com.family.calendar.service.FamilyMemberService;
import com.family.calendar.service.VisitService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService service;

    @Autowired
    private FamilyMemberService memberService;  // ✅ 가족 서비스 주입

    @Autowired
    private CommuService commuService;

    /* ✅ 방문 리스트 (특정 날짜별 / 전체) */
    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer year,
                       @RequestParam(required = false) Integer month,
                       @RequestParam(required = false) Integer day,
                       Model model,
                       HttpSession session) {

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";

        Long userId = loginUser.getUser_id();

        if (year != null && month != null && day != null) {
            String date = String.format("%04d-%02d-%02d", year, month, day);
            model.addAttribute("list", service.getVisitListByDateAndUser(date, userId));
            model.addAttribute("selectedDate", date);

            // ✅ 새로 추가
            model.addAttribute("year", year);
            model.addAttribute("month", month);
            model.addAttribute("day", day);

        } else {
            model.addAttribute("list", service.getVisitListByUser(userId));
        }

        return "visitList";
    }






    /* ✅ 방문 등록 폼 */
    @GetMapping("/insertForm")
    public String insertForm(@RequestParam(required = false) Integer year,
                             @RequestParam(required = false) Integer month,
                             @RequestParam(required = false) Integer day,
                             Model model,
                             HttpSession session) {

        // 로그인한 사용자 가져오기
        UserDto user = (UserDto) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login"; // 로그인 안 되어있으면 로그인 페이지로
        }

        // 가족 구성원 목록 전달
        model.addAttribute("members", memberService.getMembersByUser(user.getUser_id()));

        // 날짜가 선택되어 들어온 경우
        if (year != null && month != null && day != null) {
            String formattedDate = String.format("%04d-%02d-%02d", year, month, day);
            model.addAttribute("selectedDate", formattedDate);
        }

        return "visitInsertForm";
    }

    /* ✅ 방문 등록 처리 */
 // ✅ 방문 기록 등록 (user_id 포함 버전)
    @PostMapping("/insert")
    public String insert(VisitDto dto, HttpSession session) {
        // 로그인 여부 확인
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login"; // 로그인 안 된 경우 로그인 페이지로 이동

        // 🔥 로그인한 사용자 ID를 VisitDto에 주입
        dto.setUser_id(loginUser.getUser_id());

        // 서비스 계층을 통해 DB에 저장
        service.insertVisit(dto);

        // 등록 후, 해당 날짜로 이동 (캘린더나 리스트가 선택된 날짜 기준으로 갱신됨)
        LocalDate date = dto.getVisit_date();
        return String.format("redirect:/visit/list?year=%d&month=%d&day=%d",
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }


    /* ✅ 상세보기 */
    @GetMapping("/detail")
    public String detail(@RequestParam Long visit_id, Model model, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";

        Map<String, Object> params = new HashMap<>();
        params.put("visit_id", visit_id);
        params.put("user_id", loginUser.getUser_id());

        VisitDto dto = service.getVisitDetail(params);
        model.addAttribute("dto", dto);

        int year = dto.getVisit_date().getYear();
        int month = dto.getVisit_date().getMonthValue();
        int day = dto.getVisit_date().getDayOfMonth();

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);

        return "VisitDetail";
    }



    /* ✅ 수정 / 삭제 */
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
}
