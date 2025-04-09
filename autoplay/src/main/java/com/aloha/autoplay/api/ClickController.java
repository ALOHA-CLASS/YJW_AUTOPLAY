package com.aloha.autoplay.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aloha.autoplay.domain.Click;
import com.aloha.autoplay.domain.Pagination;
import com.aloha.autoplay.service.ClickService;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/click")
public class ClickController {

  @Autowired
  private ClickService clickService;

  @GetMapping()
  public ResponseEntity<?> getAll(
    @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
    @RequestParam(value = "size", required = false, defaultValue = "10") int size,
    Pagination pagination
  ) {
    try {
      PageInfo<Click> pageInfo = clickService.getPageList(page, size);
      List<Click> list = pageInfo.getList();
      pagination.setPage(page);
      pagination.setSize(size);
      pagination.setTotal(pageInfo.getTotal());
      Map<String, Object> result = new HashMap<>();
      result.put("list", list);
      result.put("pagination", pagination);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") String id) {
    try {
      Click click = clickService.getById(id);
      return new ResponseEntity<>(click, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping()
  public ResponseEntity<?> create(
    @RequestBody Click click,
    HttpSession session
  ) {
    try {
      click.setSessionTime(new java.util.Date(session.getCreationTime()));    // 세션 시간 세팅
      boolean result = clickService.create(click);
      if (result) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping()
  public ResponseEntity<?> update(@RequestBody Click click) {
    try {
      boolean result = clickService.updateById(click);
      if (result) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> destroy(@PathVariable("id") String id) {
    try {
      boolean result = clickService.deleteById(id);
      if (result) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  
}
