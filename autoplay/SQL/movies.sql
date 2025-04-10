-- 영화 테이블
DROP TABLE IF EXISTS movies;
CREATE TABLE movies (
  `no`        INT AUTO_INCREMENT PRIMARY KEY,
  `id`        VARCHAR(255) NOT NULL,
  `title`     VARCHAR(255) NOT NULL COMMENT '제목',
  `content`   TEXT COMMENT '내용',
  `type` ENUM('고시각', '저시각') NOT NULL COMMENT '타입',
	-- `preview` ENUM('프리뷰', '썸네일') NOT NULL COMMENT '프리뷰, 썸네일',
  `url`       TEXT COMMENT 'URL',
  `player_id` VARCHAR(100) COMMENT '플레이어ID',
  `full_id` VARCHAR(100) COMMENT '본편플레이어ID',
  `img`       TEXT COMMENT '이미지',
  `seq`       INT COMMENT '순서',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
) COMMENT '영화';



-- 
-- 영화 테이블에 데이터 삽입
TRUNCATE TABLE movies;
INSERT INTO movies (`id`, `title`, `content`, `type`, `preview`, `url`, `player_id`, `seq`) VALUES
(UUID(), '영화 제목 1', '영화 내용 1', '고시각', '프리뷰', 'http://example.com/movie1.mp4', 'player1', 1),
(UUID(), '영화 제목 2', '영화 내용 2', '저시각', '썸네일', 'http://example.com/movie2.mp4', 'player2', 2),
(UUID(), '영화 제목 3', '영화 내용 3', '고시각', '프리뷰', 'http://example.com/movie3.mp4', 'player3', 3);

