package com.aloha.autoplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        // 총클릭률
        model.addAttribute("clickRate", clickService.clickRate());
        // 누적오토플레이 시간
        model.addAttribute("totalAutoPlayTime", autoPlayService.total());
        model.addAttribute("todayAutoPlayTime", autoPlayService.today());
        // 누적잔류시간
        model.addAttribute("totalStayTime", useTimeService.total());
        model.addAttribute("todayStayTime", useTimeService.today());

        // 남녀별 참여자수
        model.addAttribute("genderCount", userService.genderCount());
        // 연령별 참여자수
        model.addAttribute("ageCount", userService.ageCount());

        // 단위별 오토플레이
        model.addAttribute("groupCount", autoPlayService.groupCount());
        // 단위별 잔류시간
        model.addAttribute("groupStayTime", useTimeService.groupCount());


        

        return "admin/index";
    }


    @GetMapping("/autoplay")
    public String autoplay(
        Model model
    ) throws Exception {
        log.info("/admin/autoplay");

        // 누적오토플레이 시간
        model.addAttribute("totalAutoPlayTime", autoPlayService.total());
        model.addAttribute("todayAutoPlayTime", autoPlayService.today());

        // 단위별 오토플레이
        model.addAttribute("groupCount", autoPlayService.groupCount());
        
        // [평균] 남녀별 오토플레이
        model.addAttribute("genderAvg", autoPlayService.genderAvg());
        // [평균] 연령별 오토플레이
        model.addAttribute("ageAvg", autoPlayService.ageAvg());

        return "admin/autoplay";
    }

    @GetMapping("/click")
    public String click(
        Model model
    ) throws Exception {
        log.info("/admin/click");
        // 총클릭수
        model.addAttribute("clickTotal", clickService.count());
        model.addAttribute("todayClickCount", clickService.todayCount());
        // 총클릭률
        model.addAttribute("clickRate", clickService.clickRate());

        // 남여별 클릭수
        model.addAttribute("genderClickCount", clickService.genderCount());
        // 연령별 클릭수
        model.addAttribute("ageClickCount", clickService.ageCount());
        // 남여별 평균 클릭률
        model.addAttribute("genderClickAvg", clickService.genderAvg());
        // 연령별 평균 클릭률
        model.addAttribute("ageClickAvg", clickService.ageAvg());

        return "admin/click";
    }

    @GetMapping("/usetime")
    public String usetime(
        Model model
    ) throws Exception {
        log.info("/admin/usetime");

        // 누적잔류시간
        model.addAttribute("totalStayTime", useTimeService.total());
        model.addAttribute("todayStayTime", useTimeService.today());

        // 단위별 잔류시간
        model.addAttribute("groupStayTime", useTimeService.groupCount());

        // [평균] 남녀별 잔류시간
        model.addAttribute("genderAvg", useTimeService.genderAvg());
        // [평균] 연령별 잔류시간
        model.addAttribute("ageAvg", useTimeService.ageAvg());


        return "admin/usetime";
    }
    
    
}
