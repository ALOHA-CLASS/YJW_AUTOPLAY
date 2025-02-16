-- Active: 1737895063812@@127.0.0.1@3306@autoplay
-- Active: 1732873618524@@ckitsir.cvykeuqaa8lg.ap-northeast-2.rds.amazonaws.com@3306@ckauto

TRUNCATE TABLE users;
INSERT INTO `users` (`NO`, `ID`, `USERNAME`, `PASSWORD`, `GENDER`, `BIRTH`, `ENABLED`)
VALUES 
(1, UUID(), 'user', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1990-01-01', 1),
(2, UUID(), 'admin', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', now(), 1);

TRUNCATE TABLE user_auth;
INSERT INTO `user_auth` (`no`, `username`, `auth`)
VALUES 
(1, 'user', 'ROLE_USER'),
(2, 'admin', 'ROLE_USER'),
(3, 'admin', 'ROLE_ADMIN');