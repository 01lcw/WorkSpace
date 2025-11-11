package com.family.calendar.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarDto {
    private int seq;
    private String id;
    private String title;       // 병원명
    private String extraInfo;   // 진료과
    private String content;     // 메모
    private String mdate;       // 예약 날짜 (yyyyMMddHHmm)
    private String regdate;     // 등록일시
}