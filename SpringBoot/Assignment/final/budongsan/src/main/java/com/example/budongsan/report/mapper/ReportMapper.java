package com.example.budongsan.report.mapper;

import com.example.budongsan.report.dto.ReportDto;
import com.example.budongsan.report.model.ReportParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    long selectListCount(ReportParam parameter);
    List<ReportDto> selectList(ReportParam parameter);
}
