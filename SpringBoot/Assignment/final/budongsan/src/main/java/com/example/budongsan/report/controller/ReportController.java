package com.example.budongsan.report.controller;

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
public class ReportController extends BaseController {

    private final ReportService reportService;

    @GetMapping("/report/list")
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

        return "report/list";
    }

    @GetMapping(value = {"/report/add", "/report/edit"})
    public String add(Model model, HttpServletRequest request
            , ReportInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit");
        ReportDto detail = new ReportDto();

        if (editMode) {
            long id = parameter.getId();
            ReportDto existReport = reportService.getById(id);
            if (existReport == null) {
                // error 처리
                model.addAttribute("message", "신고가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existReport;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "report/add";
    }

    @PostMapping(value = {"/report/add", "/report/edit"})
    public String addSubmit(Model model, HttpServletRequest request
            , ReportInput parameter) {

        boolean editMode = request.getRequestURI().contains("/edit");

        if (editMode) {
            long id = parameter.getId();
            ReportDto existReport = reportService.getById(id);
            if (existReport == null) {
                // error 처리
                model.addAttribute("message", "신고 정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = reportService.set(parameter);

        } else {
            boolean result = reportService.add(parameter);
        }

        return "redirect:/report/list";
    }

    @PostMapping("/report/delete")
    public String del(Model model, HttpServletRequest request
            , ReportInput parameter) {

        boolean result = reportService.del(parameter.getIdList());

        return "redirect:/report/list";
    }

}
