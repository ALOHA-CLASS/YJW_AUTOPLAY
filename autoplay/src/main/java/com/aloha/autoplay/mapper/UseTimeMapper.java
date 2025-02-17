package com.aloha.autoplay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.autoplay.domain.UseTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Mapper
public interface UseTimeMapper extends BaseMapper<UseTime> {

  // 단위별 잔류시간
  public List<Map<String, Object>> groupCount() throws Exception;

  // [평균] 남녀별 잔류시간
  public List<Map<String, Object>> genderAvg() throws Exception;

  // [평균] 연령별 잔류시간
  public List<Map<String, Object>> ageAvg() throws Exception;


}
