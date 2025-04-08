package com.aloha.autoplay.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.aloha.autoplay.domain.Movies;
import com.aloha.autoplay.domain.Pagination;
import com.aloha.autoplay.service.MovieService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("APIMovieController")
@RequestMapping("/api/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping()
  public ResponseEntity<?> getAll(
    @RequestParam(value = "page", required = false, defaultValue = "1") int page, 
    @RequestParam(value = "size", required = false, defaultValue = "10") int size,
    Pagination pagination
  ) {
    try {
      PageInfo<Movies> pageInfo = movieService.getPageList(page, size);
      List<Movies> list = pageInfo.getList();
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
      Movies movie = movieService.getById(id);
      return new ResponseEntity<>(movie, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@RequestBody Movies movie) {
    try {
      boolean result = movieService.create(movie);
      if (result) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> createForm(Movies movie) {
    try {
      boolean result = movieService.create(movie);
      if (result) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> update(@RequestBody Movies movie) {
    try {
      boolean result = movieService.updateById(movie);
      if (result) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> updateForm(Movies movie) {
    try {
      boolean result = movieService.updateById(movie);
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
      boolean result = movieService.deleteById(id);
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
