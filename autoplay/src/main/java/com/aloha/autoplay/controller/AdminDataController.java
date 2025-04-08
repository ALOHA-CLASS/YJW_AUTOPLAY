package com.aloha.autoplay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/data")
public class AdminDataController {

  @GetMapping("/autoplay")
  public String getAutoplayData() {
    log.info("Fetching autoplay data");
    return "admin/data/autoplay/index";
  }

  @GetMapping("/click")
  public String getClickData() {
    log.info("Fetching click data");
    return "admin/data/click/index";
  }

  @GetMapping("/usetime")
  public String getUsetimeData() {
    log.info("Fetching usetime data");
    return "admin/data/usetime/index";
  }

}
