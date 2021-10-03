package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.entity.Course;
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
public class BannerDto {
    Long id;

    String alterText;
    String linkURL;
    int sortValue;
    boolean newWindow;
    boolean frontDisplay;

    String filename;
    String urlFilename;

    LocalDateTime regDt; //등록일

    //추가컬럼
    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .alterText(banner.getAlterText())
                .linkURL(banner.getLinkURL())
                .sortValue(banner.getSortValue())
                .newWindow(banner.isNewWindow())
                .frontDisplay(banner.isFrontDisplay())
                .regDt(banner.getRegDt())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .build();
    }

    public static List<BannerDto> of(List<Banner> banners) {

        if (banners == null) {
            return null;
        }

        List<BannerDto> bannerDtos = new ArrayList<>();
        for(Banner x : banners) {
            bannerDtos.add(BannerDto.of(x));
        }
        return bannerDtos;
    }

}
