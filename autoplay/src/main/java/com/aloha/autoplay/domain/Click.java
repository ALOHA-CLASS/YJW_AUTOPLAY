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
@TableName("click")    // 테이블명 (Mybatis plus)
@Alias("Click")        // 별칭 (Mybatis package 생략용 - Mapper에서 사용)
public class Click {
  
  @TableId(type = IdType.AUTO)        // PK 자동증가 (Mybatis plus - insert 에서 사용)
  private Long no;                    // PK
  private String id;                  // ID
  private String username;            // 사용자ID
  private String contentName;         // 컨텐츠명(영화명)
  private Date createdAt;             // 생성일
  private Date updatedAt;             // 수정일
  
  public Click() {
      this.id = UUID.randomUUID().toString();         // UUID로 ID 생성
  }
}
