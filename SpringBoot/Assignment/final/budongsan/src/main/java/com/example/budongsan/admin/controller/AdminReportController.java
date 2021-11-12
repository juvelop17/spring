package com.example.budongsan.admin.controller;

import com.example.budongsan.controller.BaseController;
import com.example.budongsan.report.dto.ReportDto;
import com.example.budongsan.report.model.ReportInput;
import com.example.budongsan.report.model.ReportParam;
import com.example.budongsan.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminReportController extends BaseController {

    private final ReportService reportService;

    @GetMapping("/admin/report/list.do")
    public String list(Model model, ReportParam parameter) {

        parameter.init();
        List<ReportDto> reportList = reportService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(reportList)) {
            totalCount = reportList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", reportList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/report/list";
    }

    @GetMapping(value = {"/admin/report/add.do", "/admin/report/edit.do"})
    public String add(Model model, HttpServletRequest request
            , ReportInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");
        ReportDto detail = new ReportDto();

        if (editMode) {
            long id = parameter.getId();
            ReportDto existReport = reportService.getById(id);
            if (existReport == null) {
                // error 처리
                model.addAttribute("message", "공지사항이 존재하지 않습니다.");
                return "common/error";
            }
            detail = existReport;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/report/add";
    }

    @PostMapping(value = {"/admin/report/add.do", "/admin/report/edit.do"})
    public String addSubmit(Model model, HttpServletRequest request
            , ReportInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();
            ReportDto existReport = reportService.getById(id);
            if (existReport == null) {
                // error 처리
                model.addAttribute("message", "공지사항정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = reportService.set(parameter);

        } else {
            boolean result = reportService.add(parameter);
        }

        return "redirect:/admin/report/list.do";
    }

    @PostMapping("/admin/report/delete.do")
    public String del(Model model, HttpServletRequest request
            , ReportInput parameter) {

        boolean result = reportService.del(parameter.getIdList());

        return "redirect:/admin/report/list.do";
    }

}
