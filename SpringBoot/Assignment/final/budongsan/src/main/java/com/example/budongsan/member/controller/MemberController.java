package com.example.budongsan.member.controller;

import com.example.budongsan.member.dto.MemberDto;
import com.example.budongsan.member.model.MemberInput;
import com.example.budongsan.member.service.MemberService;
import com.example.budongsan.model.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/info")
    public String memberInfo(Model model, Principal principal) {

        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Model model
            , MemberInput parameter
            , Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result = memberService.updateMember(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }
        return "redirect:/member/info";
    }

    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

}
