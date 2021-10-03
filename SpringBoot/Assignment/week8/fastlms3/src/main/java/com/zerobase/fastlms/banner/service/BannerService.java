package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;

import java.util.List;

public interface BannerService {

    /**
     * 강좌 등록
     */
    boolean add(BannerInput parameter);

    /**
     * 강좌 정보수정
     */
    boolean set(BannerInput parameter);

    /**
     * 강좌 목록
     */
    List<BannerDto> list(BannerParam parameter);

    /**
     * 강좌 상세정보
     */
    BannerDto getById(long id);

    /**
     * 강좌 내용 삭제
     */
    boolean del(String idList);

}
