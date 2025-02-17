package com.aloha.autoplay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.autoplay.domain.Click;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Mapper
public interface ClickMapper extends BaseMapper<Click> {

  // 클릭률
  public Double clickRate();

  // 남여별 클릭수
  public List<Map<String, Object>> genderCount() throws Exception;

  // 연령별 클릭수
  public List<Map<String, Object>> ageCount() throws Exception;

  // 남여별 평균 클릭률
  public List<Map<String, Object>> genderAvg() throws Exception;

  // 연령별 평균 클릭률
  public List<Map<String, Object>> ageAvg() throws Exception;


}
