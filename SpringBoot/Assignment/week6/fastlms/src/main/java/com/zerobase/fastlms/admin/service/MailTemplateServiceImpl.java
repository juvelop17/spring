package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.MailTemplateDto;
import com.zerobase.fastlms.admin.entity.MailTemplate;
import com.zerobase.fastlms.admin.mapper.MailTemplateMapper;
import com.zerobase.fastlms.admin.model.MailTemplateInput;
import com.zerobase.fastlms.admin.model.MailTemplateParam;
import com.zerobase.fastlms.admin.repository.MailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MailTemplateServiceImpl implements MailTemplateService {

    private final MailTemplateRepository mailTemplateRepository;
    private final MailTemplateMapper mailTemplateMapper;

    @Override
    public List<MailTemplateDto> list(MailTemplateParam parameter) {
        List<MailTemplateDto> list = mailTemplateMapper.selectList(parameter);
        return list;
    }

    @Override
    public MailTemplateDto detail(String mailTemplateKey) {

        Optional<MailTemplate> optionalMailTemplate = mailTemplateRepository.findById(mailTemplateKey);
        if (!optionalMailTemplate.isPresent()) {
            return null;
        }

        MailTemplate mailTemplate = optionalMailTemplate.get();

        return MailTemplateDto.of(mailTemplate);
    }

    @Override
    public boolean add(MailTemplateInput parameter) {
        MailTemplate mailTemplate = MailTemplate.builder()
                .mailTemplateKey(parameter.getMailTemplateKey())
                .title(parameter.getTitle())
                .content(parameter.getContent())
                .build();
        mailTemplateRepository.save(mailTemplate);

        return true;
    }

    @Override
    public boolean update(MailTemplateInput parameter) {
        Optional<MailTemplate> optionalMailTemplate = mailTemplateRepository.findById(parameter.getMailTemplateKey());
        if (optionalMailTemplate.isPresent()) {
            MailTemplate mailTemplate = optionalMailTemplate.get();
            mailTemplate.setMailTemplateKey(parameter.getMailTemplateKey());
            mailTemplate.setTitle(parameter.getTitle());
            mailTemplate.setContent(parameter.getContent());
            mailTemplateRepository.save(mailTemplate);
        }

        return true;
    }
}
