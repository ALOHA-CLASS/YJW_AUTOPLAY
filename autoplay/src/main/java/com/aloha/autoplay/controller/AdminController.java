package com.aloha.autoplay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 관리자 페이지
     * @return
     */
    @GetMapping({"" , "/"})
    public String index() {
        log.info("/admin");
        return "admin/index";
    }


    @GetMapping("/autoplay")
    public String autoplay() {
        log.info("/admin/autoplay");
        return "admin/autoplay";
    }

    @GetMapping("/click")
    public String click() {
        log.info("/admin/click");
        return "admin/click";
    }

    @GetMapping("/usetime")
    public String usetime() {
        log.info("/admin/usetime");
        return "admin/usetime";
    }
    
    
}
