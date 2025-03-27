-- Active: 1741423366434@@127.0.0.1@3306@autoplay
-- Active: 1731904938494@@203.245.44.21@3306@alohasoft
DROP TABLE IF EXISTS `autoplay`;

CREATE TABLE `autoplay` (
	`no`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY	COMMENT 'PK',
	`id`	VARCHAR(64)	NOT NULL	COMMENT 'UK',
	`username`	VARCHAR(100)	NOT NULL	COMMENT '사용자ID',
	`content_name`	TEXT	NOT NULL	COMMENT '컨텐츠명(영화명)',
	`std_time`	TIMESTAMP	NOT NULL	COMMENT '시작시간',
	`end_time`	TIMESTAMP	NOT NULL	COMMENT '종료시간',
	`play_time`	BIGINT	NOT NULL	COMMENT '시청시간( ms, 밀리초 )',
	`created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '수정일자',
	`type` ENUM('고시각', '저시각') NOT NULL COMMENT '타입'
) COMMENT '오토플레이';


DROP TABLE IF EXISTS `click`;

CREATE TABLE `click` (
	`no`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY	COMMENT 'PK',
	`id`	VARCHAR(64)	NOT NULL	COMMENT 'UK',
	`username`	VARCHAR(100)	NOT NULL	COMMENT '사용자ID',
	`content_name`	TEXT	NOT NULL	COMMENT '컨텐츠명(영화명)',
	`created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '수정일자',
	`type` ENUM('고시각', '저시각') NOT NULL COMMENT '타입',
	`preview` ENUM('프리뷰', '썸네일') NOT NULL COMMENT '프리뷰, 썸네일'
) COMMENT '클릭';


DROP TABLE IF EXISTS `use_time`;

CREATE TABLE `use_time` (
	`no`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY	COMMENT 'PK',
	`id`	VARCHAR(64)	NOT NULL	COMMENT 'UK',
	`username`	VARCHAR(100)	NOT NULL	COMMENT '사용자ID',
	`std_time`	TIMESTAMP	NOT NULL	COMMENT '시작시간',
	`end_time`	TIMESTAMP	NOT NULL	COMMENT '종료시간',
	`login_time`	TIMESTAMP	NOT NULL	COMMENT '로그인시간',
	`use_time`	BIGINT	NOT NULL	COMMENT '잔류시간( ms, 밀리초 )',
	`created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '수정일자',
	`type` ENUM('고시각', '저시각') NOT NULL COMMENT '타입',
	`preview` ENUM('프리뷰', '썸네일') NOT NULL COMMENT '프리뷰, 썸네일'
) COMMENT '잔류시간';




--
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `NO` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `ID` VARCHAR(64) NOT NULL COMMENT 'UK',
  `USERNAME` varchar(100) NOT NULL COMMENT '아이디',
  `PASSWORD` varchar(200) NOT NULL COMMENT  '비밀번호',
  `GENDER` varchar(100) NULL COMMENT '성별',
  `BIRTH` timestamp COMMENT '생년월일',
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `UPDATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
  `ENABLED` int DEFAULT 1 COMMENT '사용여부',
  PRIMARY KEY (`NO`)
) COMMENT='회원';


--
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
      no bigint NOT NULL AUTO_INCREMENT     -- 권한번호
    , username varchar(100) NOT NULL        -- 아이디
    , auth varchar(100) NOT NULL            -- 권한 (ROLE_USER, ROLE_ADMIN, ...)
    , PRIMARY KEY(no)                      
);