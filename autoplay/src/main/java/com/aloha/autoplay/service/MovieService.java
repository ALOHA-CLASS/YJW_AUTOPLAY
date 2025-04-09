package com.aloha.autoplay.service;

import java.util.List;

import com.aloha.autoplay.domain.Movies;

public interface MovieService extends BaseService<Movies> {
    
    // 타입별 리스트
    public List<Movies> getListByType(String type) throws Exception;

    // 다음 영화
    public Movies getNextMovie(String id) throws Exception;

    // 관련 영화
    public List<Movies> getRelatedMovie(String id) throws Exception;
}
