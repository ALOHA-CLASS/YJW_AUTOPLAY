package com.aloha.autoplay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.autoplay.domain.Sample;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@Mapper
public interface SampleMapper extends BaseMapper<Sample> {

    /**
     * ⚡ MyBatis plus의 BaseMapper를 상속받으면, CRUD가 자동으로 생성됨
     * 아래 기본 CRUD 안짜도 됨
     */
    public int insert(Sample sample);

    public Sample select(@Param("name") String name);

    public int update(Sample sample);

    public int delete(@Param("name") String name);

}
