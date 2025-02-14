package com.aloha.autoplay.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.autoplay.domain.AutoPlay;
import com.aloha.autoplay.domain.Pagination;
import com.aloha.autoplay.service.AutoPlayService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/autoplay")
public class AutoPlayController {

  @Autowired private AutoPlayService autoPlayService;

  @GetMapping()
  public ResponseEntity<?> getAll(
    @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
    @RequestParam(value = "size", required = false, defaultValue = "10") int size,
    Pagination pagination
  ) {
      try {
          PageInfo<AutoPlay> pageInfo = autoPlayService.getPageList(page, size);
          List<AutoPlay> list = pageInfo.getList();
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
          AutoPlay autoPlay = autoPlayService.getById(id);
          return new ResponseEntity<>(autoPlay, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody AutoPlay autoPlay) {
      try {
          boolean result = autoPlayService.create(autoPlay);
          if( result ) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
          }
          else {
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      } catch (Exception e) {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody AutoPlay autoPlay) {
      try {
        boolean result = autoPlayService.updateById(autoPlay);
        if( result ) {
          return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        }
        else {
          return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
      } catch (Exception e) {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> destroy(@PathVariable("id") String id) {
      try {
        boolean result = autoPlayService.deleteById(id);
        if( result ) {
          return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        }
        else {
          return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  
}
