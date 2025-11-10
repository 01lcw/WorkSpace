package com.family.calendar.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthLogDto {

    private int log_id;               // 기록 ID
    private Long visit_id;            // 방문 ID (NULL 가능 → Long)
    private int member_id;            // 가족 구성원 ID (int 유지)
    private String type;              // 기록 유형 (MED / NEXT)
    private String title;             // 제목 (약 이름 or 병원명)
    private String content;           // 내용 (복용 메모 or 예약 세부내용)
    private String extra_info;        // 추가 정보 (약 용량 / 진료과 등)
    private LocalDate target_date;    // 복용일자 또는 예약일자
    private LocalDateTime record_date;// 기록 생성 시각
    private String writer;            // 작성자 이름

    // 🔹 (선택) JOIN용 가상 필드
    private String member_name;
    private String relation;
}
