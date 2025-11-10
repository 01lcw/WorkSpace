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
                sb.append("<p>")
                  .append(v.getHospital_name())
                  .append(" (")
                  .append(v.getDepartment() == null ? "" : v.getDepartment())
                  .append(")</p>");
            }
        }
        return sb.toString();
    }

    public int getLastDay(int year, int month) {
        return LocalDate.of(year, month, 1).lengthOfMonth();
    }
}
