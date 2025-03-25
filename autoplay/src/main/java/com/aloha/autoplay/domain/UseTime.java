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
@TableName("use_time")    // 테이블명 (Mybatis plus)
@Alias("UseTime")        // 별칭 (Mybatis package 생략용 - Mapper에서 사용)
public class UseTime {
  
  @TableId(type = IdType.AUTO)        // PK 자동증가 (Mybatis plus - insert 에서 사용)
  private Long no;                    // PK
  private String id;                  // ID
  private String username;             // 사용자ID
  private Date stdTime;                // 시작시간
  private Date endTime;                // 종료시간
  private Date loginTime;              // 로그인시간
  private Long useTime;                // 잔류시간 (ms, 밀리초)
  private Date createdAt;             // 생성일
  private Date updatedAt;             // 수정일
  private String type;                // 타입(저시각, 고시각) 
  private String preview;             // 미리보기(프리뷰, 썸네일)

  // 누적 잔류시간
  private Long total;
  // today 잔류시간
  private Long today;
  
  
  public UseTime() {
      this.id = UUID.randomUUID().toString();         // UUID로 ID 생성
  }
}
