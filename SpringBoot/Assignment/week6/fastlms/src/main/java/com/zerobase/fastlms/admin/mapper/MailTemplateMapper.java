package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.MailTemplateDto;
import com.zerobase.fastlms.admin.model.MailTemplateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailTemplateMapper {

    List<MailTemplateDto> selectList(MailTemplateParam parameter);

}
