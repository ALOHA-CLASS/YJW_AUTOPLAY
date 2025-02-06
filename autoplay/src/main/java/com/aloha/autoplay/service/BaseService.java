package com.aloha.autoplay.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * ⭐ 공통 서비스 메소드
 * ⚡ 업무별 기능은 해당 서비스 인터페이스를 상속받아 구현
 * 
 * ALOHA: 여러 건(리스트) 등록, 수정, 삭제 메소드 추가 고려
 * 
 */
public interface BaseService<T> {
    List<T> getList();                                  // 전체 리스트
    PageInfo<T> getPageList(int page, int pageSize);    // 페이징 리스트 (page: 페이지 번호, pageSize: 페이지당 데이터 수)
    T get(Long no);                                     // no(PK) 조회
    T getById(String id);                               // id(PK) 조회
    boolean create(T entity);                           // 생성
    boolean update(T entity);                           // 수정
    boolean updateById(T entity);                       // id(PK) 수정
    boolean delete(Long no);                            // no(PK) 삭제
    boolean deleteById(String id);                      // id(PK) 삭제
}
