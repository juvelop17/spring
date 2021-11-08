package com.example.budongsan.notice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeInput {
    long id;
    String title;
    String contents;

    //삭제를 위한
    String idList;

    LocalDateTime regDt; // 등록일
}
