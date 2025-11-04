package com.family.calendar.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommuDto {
    private Long log_id;
    private Long visit_id;
    private String writer;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
