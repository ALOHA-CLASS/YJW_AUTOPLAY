package com.aloha.autoplay.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.autoplay.domain.UserAuth;
import com.aloha.autoplay.domain.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

    // 회원 조회
    public Users select(String id) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

}
