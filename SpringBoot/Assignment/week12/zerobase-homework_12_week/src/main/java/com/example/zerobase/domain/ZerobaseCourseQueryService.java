package com.example.zerobase.domain;

import com.example.zerobase.domain.entity.ZerobaseCourse;
import com.example.zerobase.domain.repository.ZerobaseCourseRepository;
import com.example.zerobase.type.ZerobaseCourseStatus;
import com.example.zerobase.web.mock.FastcampusFakeController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZerobaseCourseQueryService {
    private final ZerobaseCourseRepository zerobaseCourseRepository;

    public List<ZerobaseCourse> getOpenedZerobaseCourseList() {
        return zerobaseCourseRepository.findOpened(ZerobaseCourseStatus.OPEN, LocalDate.now())
                .stream()
                .filter(ZerobaseCourse::exposuable)
                .collect(Collectors.toList());
    }
}
