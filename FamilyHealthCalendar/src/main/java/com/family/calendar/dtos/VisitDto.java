package com.family.calendar.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {
    private int visit_id;
    private int member_id;
    private String hospital_name;
    private String department;
    private String visit_date;
    private String memo;
    private int cost;
}
