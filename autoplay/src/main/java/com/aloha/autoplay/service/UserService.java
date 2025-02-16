package com.aloha.autoplay.service;

import java.util.Map;

import com.aloha.autoplay.domain.UserAuth;
import com.aloha.autoplay.domain.Users;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends BaseService<Users>  {

    // 🔐 로그인 
    public boolean login(Users user, HttpServletRequest request) throws Exception;
    
    // 조회
    public Users select(String username) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    // public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 총회원수 = 총참여자수
    // public long count() throws Exception;

    // today 회원수
    // public long todayCount() throws Exception;

    // 남녀별 회원수
    Map<String, Long> genderCount() throws Exception;

    // 연령별 회원수
    Map<String, Long> ageCount() throws Exception;

    


}
