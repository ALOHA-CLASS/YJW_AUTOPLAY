package com.aloha.autoplay.domain;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.type.Alias;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@TableName("movies")    // 테이블명 (Mybatis plus)
@Alias("Movies")        // 별칭 (Mybatis package 생략용 - Mapper에서 사용)
public class Movies {
    
    @TableId(type = IdType.AUTO)        // PK 자동증가 (Mybatis plus - insert 에서 사용)
    private Long no;                    // PK
    private String id;                  // ID
    private String title;               // 제목
    private String content;             // 컨텐츠명(영화명)
    private String type;                // 타입 (고시각, 저시각)
    // private String preview;          // 프리뷰, 썸네일
    private String url;                 // URL
    private String playerId;            // 플레이어ID
    private String fullId;              // 본편플레이어ID
    private String img;                 // 이미지
    private Integer seq;                // 순서
    private Date createdAt;             // 생성일
    private Date updatedAt;             // 수정일

    public Movies() {
        this.id = UUID.randomUUID().toString();         // UUID로 ID 생성
    }
}
