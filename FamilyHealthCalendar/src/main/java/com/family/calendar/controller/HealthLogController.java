package com.family.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.family.calendar.dtos.HealthLogDto;
import com.family.calendar.service.HealthLogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/health")
public class HealthLogController {

    @Autowired
    private HealthLogService service;

    // ✅ 방문별 기록 목록 조회 (VisitDetail 화면에서 사용)
    @GetMapping("/listByVisit")
    public String listByVisit(@RequestParam("visit_id") Long visit_id, Model model) {
        List<HealthLogDto> logs = service.getLogsByVisit(visit_id);
        model.addAttribute("logList", logs);
        return "VisitDetail";
    }

    // ✅ 유형별 기록 조회 (복용기록 / 예약기록)
    @GetMapping("/listByType")
    @ResponseBody
    public List<HealthLogDto> listByType(@RequestParam("type") String type,
                                         @RequestParam(value = "member_id", required = false) Integer member_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        if (member_id != null) params.put("member_id", member_id);
        return service.getLogsByType(params);
    }

    // ✅ 단일 기록 조회 (상세 보기)
    @GetMapping("/detail")
    public String detail(@RequestParam("log_id") int log_id, Model model) {
        HealthLogDto dto = service.getLogDetail(log_id);
        model.addAttribute("dto", dto);
        return "VisitDetail";
    }

    // ✅ 기록 등록 (복용기록 / 예약기록 공통)
    @PostMapping("/insert")
    public String insert(HealthLogDto dto) {
        service.insertLog(dto);
        // VisitDetail 화면으로 리다이렉트
        if (dto.getVisit_id() != null) {
            return "redirect:/visit/detail?visit_id=" + dto.getVisit_id();
        } else {
            return "redirect:/calendar";
        }
    }

    // ✅ 기록 수정
    @PostMapping("/update")
    public String update(HealthLogDto dto) {
        service.updateLog(dto);
        return "redirect:/health/detail?log_id=" + dto.getLog_id();
    }

    // ✅ 기록 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam("log_id") int log_id,
                         @RequestParam(value = "visit_id", required = false) Long visit_id) {
        service.deleteLog(log_id);
        if (visit_id != null) {
            return "redirect:/visit/detail?visit_id=" + visit_id;
        } else {
            return "redirect:/calendar";
        }
    }
}