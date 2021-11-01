package com.example.zerobase.domain.entity;

import com.example.zerobase.type.ZerobaseCourseStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "zerobase_course")
@NoArgsConstructor
public class ZerobaseCourse {

    @Id
    @Column(name = "course_id")
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ZerobaseCourseStatus status;

    private LocalDate startAt;

    private LocalDate endAt;

    private boolean hidden;

    @Builder
    public ZerobaseCourse(Long id, String name, ZerobaseCourseStatus status, LocalDate startAt, LocalDate endAt, boolean hidden) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
        this.hidden = hidden;
    }

    public boolean isSameStatus(ZerobaseCourseStatus status) {
        return this.status == status;
    }

    public boolean exposuable() {
        return !this.hidden;
    }
}
