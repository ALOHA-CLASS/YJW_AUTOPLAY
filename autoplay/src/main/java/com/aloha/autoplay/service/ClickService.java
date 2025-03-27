package com.aloha.autoplay.service;

import java.util.Map;

import com.aloha.autoplay.domain.Click;

public interface ClickService extends BaseService<Click> {

  // 클릭률
  public Double clickRate();

  // 남여별 클릭수
  Map<String, Long> genderCount() throws Exception;
  
  // 연령별 클릭수
  Map<String, Long> ageCount() throws Exception;

  // 남여별 평균 클릭률
  Map<String, Double> genderAvg() throws Exception;

  // 연령별 평균 클릭률
  Map<String, Double> ageAvg() throws Exception;

  
  /* :::::::::::::::: 타입&프리뷰 :::::::::::::::: */
  // 남여별 클릭수
  Map<String, Long> genderCount(Click click) throws Exception;
  
  // 연령별 클릭수
  Map<String, Long> ageCount(Click click) throws Exception;

  // 남여별 평균 클릭률
  Map<String, Double> genderAvg(Click click) throws Exception;

  // 연령별 평균 클릭률
  Map<String, Double> ageAvg(Click click) throws Exception;

    
    
}
