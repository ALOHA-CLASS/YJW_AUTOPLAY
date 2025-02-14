package com.aloha.autoplay.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@Alias("Users")
@TableName("users")
public class Users {
    private Long no;
    private String id;
    private String username;
    private String password;
    private String gender;
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date birth;
    private Date createdAt;
    private Date updatedAt;
    private int enabled;

    private List<UserAuth> authList;

    public Users() {
        this.id = UUID.randomUUID().toString();
    }


}