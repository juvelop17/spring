package com.example.zerobase.web.dto;

import com.example.zerobase.domain.entity.ZerobaseCourse;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ZerobaseCourseResponse {
    private Long id;
    private String name;
    private String status;
    private LocalDate startAt;
    private LocalDate endAt;

    public static ZerobaseCourseResponse of(ZerobaseCourse zerobaseCourse) {
        return ZerobaseCourseResponse
                .builder()
                .id(zerobaseCourse.getId())
                .name(zerobaseCourse.getName())
                .status(zerobaseCourse.getStatus().name())
                .startAt(zerobaseCourse.getStartAt())
                .endAt(zerobaseCourse.getEndAt())
                .build();
    }
}
