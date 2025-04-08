package com.aloha.autoplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.autoplay.domain.Movies;
import com.aloha.autoplay.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/movies")
public class MovieController {

  @Autowired MovieService movieService; 

  @GetMapping("")
  public String movies(
      Model model
  ) throws Exception {
      log.info("/admin/movies");


      return "admin/movies/index";
  }


  /**
   * 영화 등록
   * @return
   */
  @GetMapping("/create")
  public String createMovie() {
    
    return "admin/movies/create";
  }

  /**
   * 영화 수정
   * @return
   */
  @GetMapping("/{id}")
  public String editMovie(
    @PathVariable("id") String id,
    Model model
  ) {
    log.info("movie id : {}", id);
    Movies movie = movieService.getById(id);
    model.addAttribute("movie", movie);
    return "admin/movies/edit";
  }
  
}
