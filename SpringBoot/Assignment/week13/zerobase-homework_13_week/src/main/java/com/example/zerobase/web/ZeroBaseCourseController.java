package com.example.zerobase.web;

import com.example.zerobase.domain.ZerobaseCourseQueryService;
import com.example.zerobase.web.dto.ZerobaseCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ZeroBaseCourseController {

    private final ZerobaseCourseQueryService zerobaseCourseQueryService;

    @GetMapping("/v1/api/course/open")
    public List<ZerobaseCourseResponse> getZerobaseCourses() {
        return zerobaseCourseQueryService.getOpenedZerobaseCourseList()
                .stream()
                .map(ZerobaseCourseResponse::of)
                .collect(Collectors.toList());
    }
}
