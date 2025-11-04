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
    private Long member_id;
    private String hospital_name;
    private String department;
    private LocalDate visit_date;
    private Integer cost;
    private String diagnosis;
}
