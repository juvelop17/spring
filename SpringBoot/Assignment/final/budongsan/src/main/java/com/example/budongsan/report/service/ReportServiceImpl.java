package com.example.budongsan.report.service;

import com.example.budongsan.report.dto.ReportDto;
import com.example.budongsan.report.entity.Report;
import com.example.budongsan.report.mapper.ReportMapper;
import com.example.budongsan.report.model.ReportInput;
import com.example.budongsan.report.model.ReportParam;
import com.example.budongsan.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    
    private LocalDate getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
        
        }
        
        return null;
    }
    
    @Override
    public boolean add(ReportInput parameter) {

        Report report = Report.builder()
                .id(parameter.getId())
                .title(parameter.getTitle())
                .contents(parameter.getContents())
                .regDt(LocalDateTime.now())
                .build();
        reportRepository.save(report);
        
        return true;
    }
    
    @Override
    public boolean set(ReportInput parameter) {
        
        Optional<Report> optionalReport = reportRepository.findById(parameter.getId());
        if (!optionalReport.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }
        
        Report report = optionalReport.get();
        report.setTitle(parameter.getTitle());
        report.setContents(parameter.getContents());

        reportRepository.save(report);
        
        return true;
    }
    
    @Override
    public List<ReportDto> list(ReportParam parameter) {
        
        long totalCount = reportMapper.selectListCount(parameter);
        
        List<ReportDto> list = reportMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (ReportDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        
        return list;
    }
    
    @Override
    public ReportDto getById(long id) {
        return reportRepository.findById(id).map(ReportDto::of).orElse(null);
    }
    
    @Override
    public boolean del(String idList) {
        
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }
                
                if (id > 0) {
                    reportRepository.deleteById(id);
                }
            }
        }
        
        return true;
    }
    
    @Override
    public List<ReportDto> frontList(ReportParam parameter) {
        
//        if (parameter.getCategoryId() < 1) {
//            List<Report> reportList = reportRepository.findAll();
//            return ReportDto.of(reportList);
//        }
//
//        Optional<List<Report>> optionalReports = reportRepository.findByCategoryId(parameter.getCategoryId());
//        if (optionalReports.isPresent()) {
//            return ReportDto.of(optionalReports.get());
//        }
        List<Report> reportList = reportRepository.findAll();
        return ReportDto.of(reportList);
    }
    
    @Override
    public ReportDto frontDetail(long id) {
        
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isPresent()) {
            return ReportDto.of(optionalReport.get());
        }
        return null;
    }
    
    @Override
    public List<ReportDto> listAll() {
        
        List<Report> reportList = reportRepository.findAll();
        
        return ReportDto.of(reportList);
    }
    
}


























