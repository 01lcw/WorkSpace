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
public class FamilyMemberDto {
    private Long member_id;
    private Long user_id;
    private String name;
    private String relation;
    private LocalDate birth_date;
    private String gender;
}
