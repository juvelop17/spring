package com.zerobase.fastlms.admin.controller;


import com.zerobase.fastlms.admin.dto.MailTemplateDto;
import com.zerobase.fastlms.admin.model.MailTemplateInput;
import com.zerobase.fastlms.admin.model.MailTemplateParam;
import com.zerobase.fastlms.admin.service.MailTemplateService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMailTemplateController extends BaseController {

    private final MailTemplateService mailTemplateService;

    @GetMapping("/admin/mail/list.do")
    public String list(Model model, MailTemplateParam parameter) {

        parameter.init();
        List<MailTemplateDto> mailTemplates = mailTemplateService.list(parameter);
        model.addAttribute("list", mailTemplates);

        return "admin/mail/list";
    }

    @GetMapping("/admin/mail/detail.do")
    public String detail(Model model, MailTemplateParam parameter) {

        parameter.init();

        MailTemplateDto mailTemplate = mailTemplateService.detail(parameter.getMailTemplateKey());
        model.addAttribute("mailTemplate", mailTemplate);

        return "admin/mail/detail";
    }

    @PostMapping("/admin/mail/add.do")
    public String add(Model model, MailTemplateInput parameter) {

        boolean result = mailTemplateService.add(parameter);

        return "redirect:/admin/mail/list.do";
    }

    @PostMapping("/admin/mail/update.do")
    public String update(Model model, MailTemplateInput parameter) {

        boolean result = mailTemplateService.update(parameter);

        return "redirect:/admin/mail/list.do";
    }

}
