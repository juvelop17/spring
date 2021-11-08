package com.example.budongsan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {


    @RequestMapping("/")
    public String index(Model model) {

        return "index";
    }

//    private final BannerService bannerService;

//    public String index(Model model, BannerParam parameter) {
//
//        List<BannerDto> bannerDtos = bannerService.list(parameter);
//        model.addAttribute("bannerList", bannerDtos);
//
//        return "index";
//    }

    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }

}
