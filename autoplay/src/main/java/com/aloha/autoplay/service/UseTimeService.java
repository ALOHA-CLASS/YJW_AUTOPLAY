package com.aloha.autoplay.service;

import java.util.Map;

import com.aloha.autoplay.domain.UseTime;

public interface UseTimeService extends BaseService<UseTime> {

  // 누적 잔류시간 시간
  public String total();
  // today 잔류시간 시간
  public String today();

  // 단위별 잔류시간
  Map<String, Long> groupCount() throws Exception;

  // [평균] 남녀별 잔류시간
  Map<String, Long> genderAvg() throws Exception;

  // [평균] 연령별 잔류시간
  Map<String, Long> ageAvg() throws Exception;
    
    
}
