package com.example.zerobase.web.mock;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FastcampusFakeController {

    private final List<FastCampusCourse> fastCampusCourseList = createMock();

    @GetMapping("/v1/api/fastcampus/open")
    public List<FastCampusCourse> getZerobaseCourses(@RequestParam("dt") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dt) {
        return fastCampusCourseList.stream()
                .filter(FastCampusCourse::open)
                .filter(it -> it.isBetween(dt))
                .collect(Collectors.toList());
    }

    private List<FastCampusCourse> createMock() {
        return Arrays.asList(
                FastCampusCourse
                        .builder()
                        .id(100L)
                        .name("패캠 파이썬 자동화 완전 정복")
                        .status("OPEN")
                        .startAt(LocalDate.now().minusMonths(3))
                        .endAt(LocalDate.now().minusMonths(1))
                        .build(),
                FastCampusCourse
                        .builder()
                        .id(101L)
                        .name("패캠 알고리즘 완전 정복")
                        .status("OPEN")
                        .startAt(LocalDate.now().plusMonths(1))
                        .endAt(LocalDate.now().plusMonths(2))
                        .build(),
                FastCampusCourse
                        .builder()
                        .id(102L)
                        .name("패캠 스프링 종합반")
                        .status("OPEN")
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build(),
                FastCampusCourse
                        .builder()
                        .id(103L)
                        .name("패캠 쿠버네티스 마스터")
                        .status("OPEN")
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FastCampusCourse implements Serializable {
        private Long id;
        private String name;
        private String status;
        private LocalDate startAt;
        private LocalDate endAt;

        public boolean open() {
            return "OPEN".equalsIgnoreCase(status);
        }

        public boolean isBetween(LocalDate dt) {
            return startAt.isBefore(dt) && endAt.isAfter(dt);
        }
    }

}
