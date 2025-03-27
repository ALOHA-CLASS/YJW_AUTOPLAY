package com.aloha.autoplay.service;

import java.util.Map;

import com.aloha.autoplay.domain.AutoPlay;

public interface AutoPlayService extends BaseService<AutoPlay> {
    
  // 누적 오토플레이 시간
  public String total();
  // today 오토플레이 시간
  public String today();

  // 단위별 오토플레이
  Map<String, Long> groupCount() throws Exception;

  // [평균] 남녀별 오토플레이
  Map<String, Long> genderAvg() throws Exception;

  // [평균] 연령별 오토플레이
  Map<String, Long> ageAvg() throws Exception;


  /* :::::::::::::::: 타입별 :::::::::::::::: */
  // 단위별 오토플레이
  Map<String, Long> groupCount(String type) throws Exception;

  // [평균] 남녀별 오토플레이
  Map<String, Long> genderAvg(String type) throws Exception;

  // [평균] 연령별 오토플레이
  Map<String, Long> ageAvg(String type) throws Exception;
    
}
