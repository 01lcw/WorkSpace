package com.family.calendar.utils;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;
import com.family.calendar.dtos.VisitDto;

@Component
public class Util {

    public String getCalViewList(int day, List<VisitDto> list, int year, int month) {
        StringBuilder sb = new StringBuilder();
        String date = String.format("%04d-%02d-%02d", year, month, day);

        for (VisitDto v : list) {
            if (v.getVisit_date() != null && v.getVisit_date().startsWith(date)) {
                sb.append("<p style='background-color:#ffae00;color:white;"
                        + "border-radius:6px;padding:2px 4px;margin:2px 0;'>")
                  .append(v.getMember_name())
                  .append(" (")
                  .append(v.getMember_relation() == null ? "" : v.getMember_relation())
                  .append(")</p>");
            }
        }
        return sb.toString();
    }

    public int getLastDay(int year, int month) {
        return LocalDate.of(year, month, 1).lengthOfMonth();
    }
}
