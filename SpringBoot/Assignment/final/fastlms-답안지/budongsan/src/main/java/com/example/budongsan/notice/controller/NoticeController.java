package com.example.budongsan.notice.controller;

import com.example.budongsan.notice.dto.NoticeDto;
import com.example.budongsan.notice.model.NoticeParam;
import com.example.budongsan.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model, NoticeParam parameter) {

        List<NoticeDto> list = noticeService.frontList(parameter);
        model.addAttribute("list", list);

        int noticeTotalCount = 0;

        model.addAttribute("noticeTotalCount", noticeTotalCount);

        return "notice/index";
    }

    @GetMapping("/notice/{id}")
    public String noticeDetail(Model model
            , NoticeParam parameter) {

        NoticeDto detail = noticeService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "notice/detail";
    }

}
