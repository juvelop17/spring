package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.MailTemplateDto;
import com.zerobase.fastlms.admin.model.MailTemplateInput;
import com.zerobase.fastlms.admin.model.MailTemplateParam;

import java.util.List;

public interface MailTemplateService {

    List<MailTemplateDto> list(MailTemplateParam parameter);

    MailTemplateDto detail(String mailTemplateKey);

    boolean add(MailTemplateInput parameter);

    boolean update(MailTemplateInput parameter);
}
