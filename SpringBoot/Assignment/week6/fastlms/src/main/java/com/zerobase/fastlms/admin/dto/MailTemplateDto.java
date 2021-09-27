package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.MailTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailTemplateDto {

    String mailTemplateKey;
    Long mailTemplateId;
    String title;
    String content;

    public static MailTemplateDto of(MailTemplate mailTemplate) {
        return MailTemplateDto.builder()
                .mailTemplateId(mailTemplate.getMailTemplateId())
                .mailTemplateKey(mailTemplate.getMailTemplateKey())
                .title(mailTemplate.getTitle())
                .content(mailTemplate.getContent())
                .build();
    }
}
