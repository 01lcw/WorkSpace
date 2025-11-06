package com.family.calendar.mapper;

import java.util.List;
import java.util.Map;
import com.family.calendar.dtos.VisitDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VisitMapper {

    List<VisitDto> getVisitList();

    List<VisitDto> getVisitListByDate(String date);

    List<VisitDto> getVisitListByUser(Long userId);

    // ✅ 수정됨 (visit_id + user_id 둘 다 받을 수 있도록)
    VisitDto getVisitDetail(Map<String, Object> params);

    int insertVisit(VisitDto dto);
    int updateVisit(VisitDto dto);
    int deleteVisit(Long visit_id);
    int getVisitCountByDate(String yyyyMMdd);
    
    List<VisitDto> getVisitListByDateAndUser(Map<String, Object> params);
    
    int getVisitCountByDateAndUser(@Param("yyyyMMdd") String yyyyMMdd, @Param("user_id") Long userId);

}
