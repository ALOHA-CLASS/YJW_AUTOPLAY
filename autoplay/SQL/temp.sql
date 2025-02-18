-- Active: 1731904938494@@203.245.44.15@3306@ifilm


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


-- 단위별 오토플레이
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
ORDER BY play_time_range DESC
;

-- [평균] 남녀별 오토플레이

SELECT gender
      ,CAST(AVG(total_play_time) AS UNSIGNED) AS avg_play_time
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
SELECT age
      ,CAST(AVG(total_play_time) AS UNSIGNED) AS avg_play_time
FROM (
      SELECT 
            FLOOR(DATEDIFF(CURDATE(), u.birth) / 365.25 / 10) * 10 AS age,
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
GROUP BY age HAVING age IS NOT NULL
ORDER BY age
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
SELECT username, SUM(use_time) as total_play_time
FROM use_time
GROUP BY username
ORDER BY total_play_time DESC
) user_play_time
GROUP BY play_time_range
ORDER BY play_time_range

;


--
SELECT gender
      ,CAST(AVG(total_play_time) AS UNSIGNED) AS avg_play_time
FROM (
SELECT 
            u.gender,
            user_play_time.total_play_time
FROM
(
            SELECT 
            username, 
            SUM(use_time) as total_play_time
            FROM use_time
            GROUP BY username
)  user_play_time LEFT JOIN users u ON u.username = user_play_time.username
) a
GROUP BY gender HAVING gender IS NOT NULL
;


--
SELECT age
      ,CAST(AVG(total_play_time) AS UNSIGNED) AS avg_play_time
FROM (
SELECT 
            FLOOR(DATEDIFF(CURDATE(), u.birth) / 365.25 / 10) * 10 AS age,
            user_play_time.total_play_time
FROM
(
            SELECT 
            username, 
            SUM(use_time) as total_play_time
            FROM use_time
            GROUP BY username
)  user_play_time LEFT JOIN users u ON u.username = user_play_time.username
) a
GROUP BY age HAVING age IS NOT NULL
ORDER BY age
;



-- 클릭률
SELECT (SELECT COUNT(*) FROM click) / ( SELECT COUNT(*) FROM users ) * 100 as `click_rate`
FROM dual
;


-- 남여별 클릭수
SELECT gender
      ,CAST(SUM(total_click) AS UNSIGNED) AS total_click
FROM (
      SELECT 
            u.gender,
            user_click.total_click
      FROM
      (
            SELECT 
                  username, 
                  COUNT(*) as total_click
            FROM click
            GROUP BY username
      )  user_click LEFT JOIN users u ON u.username = user_click.username
) a
GROUP BY gender HAVING gender IS NOT NULL
;


-- 연령별 클릭수
SELECT age
      ,CAST(SUM(total_click) AS UNSIGNED) AS total_click
FROM (
      SELECT 
            FLOOR(DATEDIFF(CURDATE(), u.birth) / 365.25 / 10) * 10 AS age,
            user_click.total_click
      FROM
      (
            SELECT 
                  username, 
                  COUNT(*) as total_click
            FROM click
            GROUP BY username
      )  user_click LEFT JOIN users u ON u.username = user_click.username
) a
GROUP BY age HAVING age IS NOT NULL
ORDER BY age
;


-- 남여별 평균 클릭률
SELECT gender
      ,CAST(SUM(total_click) AS UNSIGNED)  
            / 
      ( SELECT COUNT(*) * 12 FROM users WHERE gender = a.gender ) * 100   AS avg_click
FROM (
      SELECT 
            u.gender,
            CASE 
                  WHEN user_click.total_click > 12 THEN 12 
                  ELSE user_click.total_click 
            END AS total_click 
      FROM
      (
            SELECT 
                  username, 
                  COUNT(*) as total_click
            FROM click
            GROUP BY username
      )  user_click LEFT JOIN users u ON u.username = user_click.username
) a
GROUP BY gender HAVING gender IS NOT NULL
;


-- 연령별 평균 클릭률
SELECT age
      ,CAST(SUM(total_click) AS UNSIGNED) 
            / 
      ( SELECT COUNT(*) * 12 FROM users WHERE FLOOR(DATEDIFF(CURDATE(), birth) / 365.25 / 10) * 10 = a.age ) * 100   AS avg_click
FROM (
      SELECT 
            FLOOR(DATEDIFF(CURDATE(), u.birth) / 365.25 / 10) * 10 AS age,
            CASE 
                  WHEN user_click.total_click > 12 THEN 12 
                  ELSE user_click.total_click 
            END AS total_click 
      FROM
      (
            SELECT 
                  username, 
                  COUNT(*) as total_click
            FROM click
            GROUP BY username
      )  user_click LEFT JOIN users u ON u.username = user_click.username
) a
GROUP BY age HAVING age IS NOT NULL
ORDER BY age
;



--
SELECT SUM(total_click) AS total_click
FROM (
      SELECT 
            CASE 
                  WHEN user_click.total_click > 12 THEN 12 
                  ELSE user_click.total_click 
            END AS total_click 
      FROM
      (
            SELECT 
                  username, 
                  COUNT(*) as total_click
            FROM click
            GROUP BY username
      )  user_click LEFT JOIN users u ON u.username = user_click.username
) a
;


--
SELECT ROUND(
            (
                SELECT SUM(total_click) AS total_click
                FROM (
                    SELECT 
                            CASE 
                                WHEN user_click.total_click > 12 THEN 12 
                                ELSE user_click.total_click 
                            END AS total_click 
                    FROM
                    (
                            SELECT 
                                username, 
                                COUNT(*) as total_click
                            FROM click
                            GROUP BY username
                    )  user_click LEFT JOIN users u ON u.username = user_click.username
                ) a
            )
        / (( SELECT COUNT(*) FROM users ) * 12) * 100, 2) 
            as `click_rate`
        FROM dual
;


--
SELECT gender
      ,
      ROUND( 
            CAST(SUM(total_click) AS UNSIGNED)  
                  / 
            ( SELECT COUNT(*) * 12 FROM users WHERE (a.gender = '기타' AND gender IS NULL) OR gender = a.gender ) * 100   
            , 2)
      AS avg_click
      FROM (
            SELECT 
                        IFNULL( u.gender, '기타' ) gender,
                        CASE 
                        WHEN user_click.total_click > 12 THEN 12 
                        ELSE user_click.total_click 
                        END AS total_click 
            FROM
            (
                        SELECT 
                        username, 
                        COUNT(*) as total_click
                        FROM click
                        GROUP BY username
            )  user_click LEFT JOIN users u ON u.username = user_click.username
      ) a
      GROUP BY gender HAVING gender IS NOT NULL;

      


--
SELECT age
            ,
            ROUND(
                CAST(SUM(total_click) AS UNSIGNED) 
                        / 
                ( SELECT COUNT(*) * 12 FROM users WHERE FLOOR(DATEDIFF(CURDATE(), birth) / 365.25 / 10) * 10 = a.age ) * 100   
                , 2
            ) AS avg_click

        FROM (
            SELECT 
                    IFNULL(FLOOR(DATEDIFF(CURDATE(), u.birth) / 365.25 / 10) * 10, 0)  AS age,
                    CASE 
                        WHEN user_click.total_click > 12 THEN 12 
                        ELSE user_click.total_click 
                    END AS total_click 
            FROM
            (
                    SELECT 
                        username, 
                        COUNT(*) as total_click
                    FROM click
                    GROUP BY username
            )  user_click LEFT JOIN users u ON u.username = user_click.username
        ) a
        GROUP BY age HAVING age IS NOT NULL
        ORDER BY age;


--
SELECT gender
            ,CAST(AVG(total_play_time) AS UNSIGNED) AS avg_use_time
        FROM (
            SELECT 
                    IFNULL( u.gender, '기타' ) gender,
                    use_play_time.total_play_time
            FROM
            (
                    SELECT 
                        username, 
                        SUM(use_time) as total_play_time
                    FROM use_time
                    GROUP BY username
            )  use_play_time LEFT JOIN users u ON u.username = use_play_time.username
        ) a
        GROUP BY gender HAVING gender IS NOT NULL;