package com.example.budongsan.notice.mapper;

import com.example.budongsan.notice.dto.NoticeDto;
import com.example.budongsan.notice.model.NoticeParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    long selectListCount(NoticeParam parameter);
    List<NoticeDto> selectList(NoticeParam parameter);
}
