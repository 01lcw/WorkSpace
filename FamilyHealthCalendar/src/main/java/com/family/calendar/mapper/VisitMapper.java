package com.family.calendar.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.family.calendar.dtos.VisitDto;

@Mapper
public interface VisitMapper {

    List<VisitDto> getVisitListByUser(int user_id);

    VisitDto getVisitDetail(int visit_id);

    void insertVisit(VisitDto dto);

    void deleteVisit(int visit_id);
}
