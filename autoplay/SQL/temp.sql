-- Active: 1737895063812@@127.0.0.1@3306@autoplay


SELECT *
FROM users
WHERE DATE_FORMAT(`CREATED_AT`, '%y%m%d') = DATE_FORMAT(NOW(), '%y%m%d')
;

SELECT SUM(play_time) as today
FROM autoplay
WHERE DATE_FORMAT(`CREATED_AT`, '%y%m%d') = DATE_FORMAT(NOW(), '%y%m%d')
;

SELECT * FROM users;

SELECT gender
      ,COUNT(*)
FROM users
GROUP BY gender HAVING `GENDER` IS NOT NULL
;

SELECT birth
      ,COUNT(*)
FROM users
GROUP BY birth HAVING `BIRTH` IS NOT NULL
;

SELECT 
    FLOOR(DATEDIFF(CURDATE(), birth) / 365.25 / 10) * 10 AS age,
    COUNT(*) AS count
FROM users
GROUP BY age HAVING age IS NOT NULL
ORDER BY age;


--

SELECT 
      CASE 
            WHEN play_time < (1 * 1000 * 60) THEN '0~1 분'
            WHEN play_time < (3 * 1000 * 60) THEN '1~3 분'
            WHEN play_time < (5 * 1000 * 60) THEN '3~5 분'
            WHEN play_time < (10 * 1000 * 60) THEN '5~10 분'
            WHEN play_time < (15 * 1000 * 60) THEN '10~15 분'
            WHEN play_time < (20 * 1000 * 60) THEN '15~20 분'
            ELSE '20분~'
      END AS play_time_range,
      COUNT(*) AS count
FROM autoplay
GROUP BY play_time_range
ORDER BY play_time_range
;


--
SELECT 
      CASE 
            WHEN total_play_time < (1 * 1000 * 60) THEN '0~1 분'
            WHEN total_play_time < (3 * 1000 * 60) THEN '1~3 분'
            WHEN total_play_time < (5 * 1000 * 60) THEN '3~5 분'
            WHEN total_play_time < (10 * 1000 * 60) THEN '5~10 분'
            WHEN total_play_time < (15 * 1000 * 60) THEN '10~15 분'
            WHEN total_play_time < (20 * 1000 * 60) THEN '15~20 분'
            ELSE '20분~'
      END AS play_time_range,
      COUNT(*) AS count
FROM
(
      SELECT username, SUM(play_time) as total_play_time
      FROM autoplay
      GROUP BY username
      ORDER BY total_play_time DESC
) user_play_time
GROUP BY play_time_range
ORDER BY play_time_range
;

-- [평균] 남녀별 오토플레이
SELECT 
      gender,
      AVG(total_play_time) AS avg_play_time
FROM
users LEFT JOIN
(
      SELECT 
            username, 
            SUM(play_time) as total_play_time
      FROM autoplay
      GROUP BY username
) user_play_time ON users.username = user_play_time.username
GROUP BY gender
HAVING gender IS NOT NULL
ORDER BY gender;

SELECT gender
      ,AVG(total_play_time) AS avg_play_time
FROM (
      SELECT 
            u.gender,
            user_play_time.total_play_time
      FROM
      (
            SELECT 
                  username, 
                  SUM(play_time) as total_play_time
            FROM autoplay
            GROUP BY username
      )  user_play_time LEFT JOIN users u ON u.username = user_play_time.username
) a
GROUP BY gender HAVING gender IS NOT NULL
;


-- [평균] 연령별 오토플레이