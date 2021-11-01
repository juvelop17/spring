package com.example.zerobase.domain.repository;

import com.example.zerobase.domain.entity.ZerobaseCourse;
import com.example.zerobase.type.ZerobaseCourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ZerobaseCourseRepository extends JpaRepository<ZerobaseCourse, Long> {

    Optional<ZerobaseCourse> findById(Long id);

    List<ZerobaseCourse> findAll();

    @Query("select course from ZerobaseCourse course where course.status = :status and course.startAt <= :now and :now <= course.endAt")
    List<ZerobaseCourse> findOpened(@Param("status") ZerobaseCourseStatus status, @Param("now") LocalDate now);
}
