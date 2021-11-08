package com.example.budongsan.notice.dto;

import com.example.budongsan.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NoticeDto {
    long id;
    String title;
    String contents;

    LocalDateTime regDt; // 등록일

    //추가컬럼
    long totalCount;
    long seq;


    public static NoticeDto of(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .contents(notice.getContents())
                .regDt(notice.getRegDt())
                .build();
    }

    public static List<NoticeDto> of(List<Notice> notices) {
        if (notices == null) {
            return null;
        }

        List<NoticeDto> noticeDtos = new ArrayList<>();
        for (Notice x : notices) {
            noticeDtos.add(NoticeDto.of(x));
        }
        return noticeDtos;
    }

}
