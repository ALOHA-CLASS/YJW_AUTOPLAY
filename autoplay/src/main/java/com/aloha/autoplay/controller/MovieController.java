package com.aloha.autoplay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/movies")
public class MovieController {

  @RequestMapping("/create")
  public String createMovie() {
    
    return "admin/movies/create";
  }
  
}
