package com.aloha.autoplay.domain;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@TableName("sample")    // 테이블명 (Mybatis plus)
@Alias("Sample")        // 별칭 (Mybatis package 생략용 - Mapper에서 사용)
public class Sample {
    
    @TableId(type = IdType.AUTO)        // PK 자동증가 (Mybatis plus - insert 에서 사용)
    private Long no;                    // PK
    private String id;                  // ID
    private String name;                // 이름
    private int value;                  // 값
    private Date createdAt;             // 생성일
    private Date updatedAt;             // 수정일

    public Sample() {
        this.id = UUID.randomUUID().toString();         // UUID로 ID 생성
    }
}
