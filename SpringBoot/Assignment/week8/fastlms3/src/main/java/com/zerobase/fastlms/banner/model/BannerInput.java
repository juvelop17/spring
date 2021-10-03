package com.zerobase.fastlms.banner.model;

import lombok.Data;

@Data
public class BannerInput {
    Long id;

    String alterText;
    String linkURL;
    int sortValue;
    boolean newWindow;
    boolean frontDisplay;

    String filename;
    String urlFilename;

    //삭제를 위한
    String idList;
}
