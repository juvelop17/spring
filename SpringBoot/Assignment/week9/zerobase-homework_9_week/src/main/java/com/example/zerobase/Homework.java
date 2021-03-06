package com.example.zerobase;

import com.example.zerobase.domain.ZerobaseCourse;
import com.example.zerobase.domain.ZerobaseCourseRepository;
import com.example.zerobase.type.ZerobaseCourseStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Homework {
    private final ZerobaseCourseRepository repository;

    // TODO: 테스트가 통과할 수 있도록 아래 메서드 들을 작성하세요.

    public Optional<ZerobaseCourse> getZerobaseCourse(Long id) {
        // TODO: id 가 일치하며, hidden = false 인 강의만 조회되어야 함
        return Optional.ofNullable(repository.findById(id))
                .filter(it -> !it.isHidden());
    }

    public List<ZerobaseCourse> getZerobaseCourse(String status) {
        // TODO: status가 일치하고, hidden = false 인 강의들이 조회되어야 함
        return repository.findAll()
                .stream()
                .filter(it -> !it.isHidden())
                .filter(it -> it.isSameStatus(ZerobaseCourseStatus.valueOf(status)))
                .collect(Collectors.toList());
    }

    public List<ZerobaseCourse> getOpenZerobaseCourse(LocalDate targetDt) {
        // TODO: status = "OPEN" 이고, hidden = false 이며,
        //  startAt <= targetDt && targetDt <= endAt 인 강의만 조회되어야함.
        return repository.findAll()
                .stream()
                .filter(it -> !it.isHidden())
                .filter(it -> it.isOpen(targetDt))
                .collect(Collectors.toList());
    }
}
