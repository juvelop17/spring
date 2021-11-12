package com.example.budongsan.report.service;

import com.example.budongsan.report.dto.ReportDto;
import com.example.budongsan.report.model.ReportInput;
import com.example.budongsan.report.model.ReportParam;

import java.util.List;

public interface ReportService {

    /**
     * 공지사항 등록
     */
    boolean add(ReportInput parameter);
    
    /**
     * 공지사항 정보수정
     */
    boolean set(ReportInput parameter);
    
    /**
     * 공지사항 목록
     */
    List<ReportDto> list(ReportParam parameter);
    
    /**
     * 공지사항 상세정보
     */
    ReportDto getById(long id);
    
    /**
     * 공지사항 내용 삭제
     */
    boolean del(String idList);
    
    /**
     * 프론트 공지사항 목록
     */
    List<ReportDto> frontList(ReportParam parameter);
    
    /**
     * 프론트 공지사항 상세 정보
     */
    ReportDto frontDetail(long id);
    

    /**
     * 전체 공지사항 목록
     */
    List<ReportDto> listAll();
    
}
