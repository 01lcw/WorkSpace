package com.family.calendar.controller;

import com.family.calendar.dtos.CommuDto;
import com.family.calendar.service.CommuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 가족 커뮤니케이션 로그 관리 컨트롤러
 * - 특정 visit_id에 속한 대화 기록 목록 조회
 * - 새로운 대화(메모) 등록
 */
@Controller
@RequestMapping("/commu")
public class CommuController {

    @Autowired
    private CommuService commuService;

    /**
     * 특정 visit_id에 대한 커뮤니케이션 로그 목록 보기
     * (직접 접근 시 테스트용으로 사용 가능)
     */
    @GetMapping("/list")
    public String list(@RequestParam Long visit_id, Model model) {
        model.addAttribute("visit_id", visit_id);
        model.addAttribute("logList", commuService.getLogsByVisit(visit_id));
        return "commuList";  // templates/commuList.html
    }

    /**
     * 커뮤니케이션 로그 등록 처리
     * visitDetail.html의 form에서 호출
     */
    @PostMapping("/insert")
    public String insert(CommuDto dto) {
        commuService.insertLog(dto);

        // 등록 후 해당 방문 상세 페이지로 리다이렉트
        return "redirect:/visit/detail?visit_id=" + dto.getVisit_id();
    }
}
