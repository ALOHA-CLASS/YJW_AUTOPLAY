-- Active: 1737895063812@@127.0.0.1@3306@aloha
DROP TABLE IF EXISTS sample;

-- 샘플 테이블 생성
CREATE TABLE sample (
    no BIGINT AUTO_INCREMENT PRIMARY KEY,  -- PK 자동증가
    id VARCHAR(255) NOT NULL,              -- ID
    name VARCHAR(255) NOT NULL,            -- 이름
    value INT NOT NULL,                     -- 값
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성일자
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 수정일자
);

-- 데이터 삽입 X 10 번 실행해서 여러 데이터 생성해보세요.
INSERT INTO sample (id, name, value) VALUES
(UUID(), 'name1', 10),
(UUID(), 'name2', 20),
(UUID(), 'name3', 30),
(UUID(), 'name4', 40),
(UUID(), 'name5', 50),
(UUID(), 'name6', 60),
(UUID(), 'name7', 70),
(UUID(), 'name8', 80),
(UUID(), 'name9', 90),
(UUID(), 'name10', 100);

-- board 샘플 데이터
SELECT * FROM boards;
INSERT INTO boards (id, user_no, title, content, view_count, like_count, comment_count ) VALUES
(UUID(), 1, 'title1', 'content1', 0, 0, 0),
(UUID(), 2, 'title2', 'content2', 0, 0, 0),
(UUID(), 3, 'title3', 'content3', 0, 0, 0),
(UUID(), 4, 'title4', 'content4', 0, 0, 0),
(UUID(), 5, 'title5', 'content5', 0, 0, 0),
(UUID(), 6, 'title6', 'content6', 0, 0, 0),
(UUID(), 7, 'title7', 'content7', 0, 0, 0),
(UUID(), 8, 'title8', 'content8', 0, 0, 0),
(UUID(), 9, 'title9', 'content9', 0, 0, 0),
(UUID(), 10, 'title10', 'content10', 0, 0, 0);