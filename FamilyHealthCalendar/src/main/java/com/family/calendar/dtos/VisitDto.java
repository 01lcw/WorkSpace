package com.family.calendar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitDto {
    private Long visit_id;
    private int user_id;
    private Long member_id;
    private String member_name;
    private String member_relation;
    private String hospital_name;
    private String department;
    private String visit_date;
    private int cost;
    private String diagnosis;
}