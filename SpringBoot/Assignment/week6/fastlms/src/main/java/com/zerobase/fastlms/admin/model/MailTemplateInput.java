package com.zerobase.fastlms.admin.model;


import lombok.Data;

@Data
public class MailTemplateInput {
    String mailTemplateKey;
    String title;
    String content;
}
