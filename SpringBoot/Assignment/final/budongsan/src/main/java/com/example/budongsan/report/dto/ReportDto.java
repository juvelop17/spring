package com.example.budongsan.report.dto;

import com.example.budongsan.report.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReportDto {
    long id;
    String title;
    String contents;

    LocalDateTime regDt; // 등록일

    //추가컬럼
    long totalCount;
    long seq;


    public static ReportDto of(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .contents(report.getContents())
                .regDt(report.getRegDt())
                .build();
    }

    public static List<ReportDto> of(List<Report> reports) {
        if (reports == null) {
            return null;
        }

        List<ReportDto> reportDtos = new ArrayList<>();
        for (Report x : reports) {
            reportDtos.add(ReportDto.of(x));
        }
        return reportDtos;
    }

}
