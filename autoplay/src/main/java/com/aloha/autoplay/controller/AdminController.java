package com.aloha.autoplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.autoplay.domain.UseTime;
import com.aloha.autoplay.service.AutoPlayService;
import com.aloha.autoplay.service.ClickService;
import com.aloha.autoplay.service.UseTimeService;
import com.aloha.autoplay.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired UserService userService;
    @Autowired AutoPlayService autoPlayService;
    @Autowired ClickService clickService;
    @Autowired UseTimeService useTimeService;

    /**
     * 관리자 페이지
     * @return
    * @throws Exception 
    */
    @GetMapping({"" , "/"})
    public String index(
        Model model
    ) throws Exception {
        log.info("/admin");
        // 총참여자수
        model.addAttribute("userTotal", userService.count());
        model.addAttribute("todayUserCount", userService.todayCount());
        // 총클릭수
        model.addAttribute("clickTotal", clickService.count());
        model.addAttribute("todayClickCount", clickService.todayCount());
        // 누적오토플레이 시간
        model.addAttribute("totalAutoPlayTime", autoPlayService.total());
        model.addAttribute("todayAutoPlayTime", autoPlayService.today());
        // 누적잔류시간

        // 남녀별 참여자수
        model.addAttribute("genderCount", userService.genderCount());
        // 연령별 참여자수
        model.addAttribute("ageCount", userService.ageCount());
        

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
