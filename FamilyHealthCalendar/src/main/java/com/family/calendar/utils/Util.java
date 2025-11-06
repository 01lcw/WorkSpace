package com.family.calendar.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.family.calendar.dtos.VisitDto;

@Component
public class Util {

    /** ✅ 1. 한 자리 문자열일 경우 앞에 0을 붙여 두 자리로 만듭니다. */
    public String isTwo(String s) {
        return (s.length() < 2) ? "0" + s : s;
    }

    /** ✅ 2. yyyyMMddHHmm → 지정한 포맷으로 변환 ("yyyy-MM-dd HH:mm" 등) */
    public String toDates(String date, String pattern) {
        if (date == null || date.length() < 12) return "";

        String formatted = date.substring(0, 4) + "-" +
                           date.substring(4, 6) + "-" +
                           date.substring(6, 8) + " " +
                           date.substring(8, 10) + ":" +
                           date.substring(10, 12) + ":00";

        try {
            Timestamp ts = Timestamp.valueOf(formatted);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(ts);
        } catch (IllegalArgumentException e) {
            return ""; // 잘못된 날짜 포맷일 경우 빈 문자열 반환
        }
    }

    /** ✅ 3. 달력용 하루 방문 내역 문자열 생성 (병원명 표시) */
    public String getVisitListForDay(int day, List<VisitDto> vlist) {
        if (vlist == null || vlist.isEmpty()) return "";

        String d = isTwo(String.valueOf(day));  // ex: "01"
        StringBuilder visitList = new StringBuilder();

        for (VisitDto visit : vlist) {
            LocalDate visitDate = visit.getVisit_date();

            if (visitDate != null && String.format("%02d", visitDate.getDayOfMonth()).equals(d)) {
                String hospital = visit.getHospital_name() != null ? visit.getHospital_name() : "병원명 없음";
                visitList.append("<p>")
                         .append(hospital.length() > 7 ? hospital.substring(0, 7) + ".." : hospital)
                         .append("</p>");
            }
        }

        return visitList.toString();
    }
    public String getCalViewList(int day, List<VisitDto> list, int year, int month) {
        StringBuilder sb = new StringBuilder();
        List<VisitDto> dailyList = list.stream()
            .filter(v -> v.getVisit_date().equals(LocalDate.of(year, month, day)))
            .collect(Collectors.toList());

        int count = 0;
        StringBuilder tooltip = new StringBuilder(); // 툴팁 전체 목록

        for (VisitDto v : dailyList) {
            tooltip.append(v.getMember_name())
                   .append(" (").append(v.getMember_relation()).append(")\n");
            if (count < 2) { // 최대 2개까지만 표시
                sb.append(v.getMember_name())
                  .append(" (").append(v.getMember_relation()).append(")<br>");
            }
            count++;
        }

        if (count > 2) {
            sb.append("외 ").append(count - 2).append("건...");
        }

        // 🔹 최종 HTML (툴팁 포함)
        if (count > 0) {
            return String.format("<div title='%s'>%s</div>",
                    tooltip.toString().trim(), sb.toString());
        } else {
            return "";
        }
    }



    
    

}
