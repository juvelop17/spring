package com.example.zerobase.domain;

import com.example.zerobase.type.ZerobaseCourseStatus;
import com.example.zerobase.web.advice.ExceptionCode;
import com.example.zerobase.web.advice.ZerobaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZerobaseCourseQueryService {
    private final ZerobaseCourseRepository zerobaseCourseRepository;

    public ZerobaseCourse getOrThrow(Long id) {
        return zerobaseCourseRepository.findById(id)
                .filter(it -> !it.isHidden())
                .orElseThrow(() -> new ZerobaseException(ExceptionCode.NOT_FOUND_COURSE)); // TODO 적당히 Exception을 바꿔보세요
    }

    public List<ZerobaseCourse> getZerobaseCourseList(ZerobaseCourseStatus status) {

        checkStatus(status);

        return zerobaseCourseRepository.findAll()
                .stream()
                .filter(it -> !it.isHidden())
                .filter(it -> it.isSameStatus(status))
                .collect(Collectors.toList());
    }

    private void checkStatus(ZerobaseCourseStatus status) {
        if (status.isUnknown())
            throw new ZerobaseException(ExceptionCode.INVALID_COURSE_STATUS); // TODO 적당히 Exception을 바꿔보세요

        if (status.isClose())
            throw new ZerobaseException(ExceptionCode.ALREADY_CLOSED); // TODO 적당히 Exception을 바꿔보세요
    }
}
