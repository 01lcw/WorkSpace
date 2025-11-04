package com.family.calendar.mapper;

import java.util.List;
import com.family.calendar.dtos.VisitDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisitMapper {
    List<VisitDto> getVisitList();
    VisitDto getVisitDetail(Long visit_id);
    int insertVisit(VisitDto dto);
    int updateVisit(VisitDto dto);
    int deleteVisit(Long visit_id);
    
    List<VisitDto> getVisitListByDate(String date);
}
