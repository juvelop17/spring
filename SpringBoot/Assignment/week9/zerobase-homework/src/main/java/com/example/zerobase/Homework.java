package com.example.zerobase;

import com.example.zerobase.domain.ZerobaseCourse;
import com.example.zerobase.domain.ZerobaseCourseRepository;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
public class Homework {
    private final ZerobaseCourseRepository repository;

    private enum CourseStatus {IN_PROGRESS, CLOSE, OPEN};

    // TODO: 테스트가 통과할 수 있도록 아래 메서드 들을 작성하세요.

    public Optional<ZerobaseCourse> getZerobaseCourse(Long id) {
        // TODO: id 가 일치하며, hidden = false 인 강의만 조회되어야 함

        Optional<ZerobaseCourse> optionalZerobaseCourse = Optional.ofNullable(repository.findById(id));
        if (optionalZerobaseCourse.isEmpty()) {
            return Optional.empty();
        }

        if (optionalZerobaseCourse.get().isHidden()) {
            return Optional.empty();
        }

        return optionalZerobaseCourse;
    }

    private boolean compareStatus(ZerobaseCourse zerobaseCourse, String status) {
        return CourseStatus.valueOf(status) == CourseStatus.valueOf(zerobaseCourse.getStatus());
    }

    public List<ZerobaseCourse> getZerobaseCourse(String status) {
        // TODO: status가 일치하고, hidden = false 인 강의들이 조회되어야 함

        List<ZerobaseCourse> zerobaseCourseList = repository.findAll();

        // 질문 : enum을 사용해봤는데, 이런식으로 사용하면 될까요?
        // 질문 : 이 상황에서 enum은 어디에서 선언하는게 가장 좋은가요?
        List<ZerobaseCourse> selectedCourseList = new ArrayList<>();
        for (ZerobaseCourse zerobaseCourse: zerobaseCourseList) {
            if (compareStatus(zerobaseCourse, status) && !zerobaseCourse.isHidden()) {
                selectedCourseList.add(zerobaseCourse);
            }
        }

        return selectedCourseList;
    }

    private boolean compareDate(ZerobaseCourse zerobaseCourse, LocalDate targetDt) {
        return (zerobaseCourse.getStartAt().isBefore(targetDt) || zerobaseCourse.getStartAt().isEqual(targetDt))
                && (zerobaseCourse.getEndAt().isAfter(targetDt) || zerobaseCourse.getEndAt().isEqual(targetDt));
    }

    public List<ZerobaseCourse> getOpenZerobaseCourse(LocalDate targetDt) {
        // TODO: status = "OPEN" 이고, hidden = false 이며,
        //  startAt <= targetDt && targetDt <= endAt 인 강의만 조회되어야함.

        List<ZerobaseCourse> zerobaseCourseList = repository.findAll();

        List<ZerobaseCourse> selectedCourseList = new ArrayList<>();
        for (ZerobaseCourse zerobaseCourse: zerobaseCourseList) {
            if (compareStatus(zerobaseCourse, "OPEN") && !zerobaseCourse.isHidden()
                    && compareDate(zerobaseCourse, targetDt)) {
                selectedCourseList.add(zerobaseCourse);
            }
        }

        return selectedCourseList;
    }
}
