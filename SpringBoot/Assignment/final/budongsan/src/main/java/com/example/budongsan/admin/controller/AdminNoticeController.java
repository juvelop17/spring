package com.example.budongsan.admin.controller;

import com.example.budongsan.controller.BaseController;
import com.example.budongsan.notice.dto.NoticeDto;
import com.example.budongsan.notice.model.NoticeInput;
import com.example.budongsan.notice.model.NoticeParam;
import com.example.budongsan.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminNoticeController extends BaseController {

    private final NoticeService noticeService;

    @GetMapping("/admin/notice/list.do")
    public String list(Model model, NoticeParam parameter) {

        parameter.init();
        List<NoticeDto> noticeList = noticeService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(noticeList)) {
            totalCount = noticeList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", noticeList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/notice/list";
    }

    @GetMapping(value = {"/admin/notice/add.do", "/admin/notice/edit.do"})
    public String add(Model model, HttpServletRequest request
            , NoticeInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        NoticeDto detail = new NoticeDto();

        if (editMode) {
            long id = parameter.getId();
            NoticeDto existNotice = noticeService.getById(id);
            if (existNotice == null) {
                // error ??????
                model.addAttribute("message", "??????????????? ???????????? ????????????.");
                return "common/error";
            }
            detail = existNotice;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/notice/add";
    }

    @PostMapping(value = {"/admin/notice/add.do", "/admin/notice/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , NoticeInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            NoticeDto existNotice = noticeService.getById(id);
            if (existNotice == null) {
                // error ??????
                model.addAttribute("message", "????????????????????? ???????????? ????????????.");
                return "common/error";
            }

            boolean result = noticeService.set(parameter);

        } else {
            boolean result = noticeService.add(parameter);
        }

        return "redirect:/admin/notice/list.do";
    }

    @PostMapping("/admin/notice/delete.do")
    public String del(Model model, HttpServletRequest request
            , NoticeInput parameter) {

        boolean result = noticeService.del(parameter.getIdList());

        return "redirect:/admin/notice/list.do";
    }

}
