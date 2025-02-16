package com.aloha.autoplay.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.autoplay.domain.AutoPlay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Mapper
public interface AutoPlayMapper extends BaseMapper<AutoPlay> {

  // 단위별 오토플레이
  public Map<String, Long> groupCount() throws Exception;

  // [평균] 남녀별 오토플레이
  public Map<String, Long> genderAvg() throws Exception;

  // [평균] 연령별 오토플레이
  public Map<String, Long> ageAvg() throws Exception;

}
