package com.zerobase.fastlms.banner.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String alterText;
    String linkURL;
    int sortValue;
    boolean newWindow;
    boolean frontDisplay;

    LocalDateTime regDt;//등록일(추가날짜)

    String filename;
    String urlFilename;
}
