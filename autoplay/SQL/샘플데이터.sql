-- Active: 1731904938494@@203.245.44.15@3306@ifilm
-- Active: 1731384027892@@127.0.0.1@3306@autoplay

TRUNCATE TABLE users;
INSERT INTO `users` (`NO`, `ID`, `USERNAME`, `PASSWORD`, `GENDER`, `BIRTH`, `ENABLED`)
VALUES 
(1, UUID(), 'user', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', NULL, NULL, 1),
(2, UUID(), 'admin', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', NULL, NULL, 1);

TRUNCATE TABLE user_auth;
INSERT INTO `user_auth` (`no`, `username`, `auth`)
VALUES 
(1, 'user', 'ROLE_USER'),
(2, 'admin', 'ROLE_USER'),
(3, 'admin', 'ROLE_ADMIN');



--------------테스트 계정

INSERT INTO `users` (`NO`, `ID`, `USERNAME`, `PASSWORD`, `GENDER`, `BIRTH`, `ENABLED`)
VALUES 
(3, UUID(), 'test1', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '남자', '2010-01-01 00:00:00', 1),
(4, UUID(), 'test2', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '2000-01-01 00:00:00', 1),
(5, UUID(), 'test3', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '남자', '1990-01-01 00:00:00', 1),
(6, UUID(), 'test4', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1980-01-01 00:00:00', 1),
(7, UUID(), 'test5', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '남자', '1988-01-01 00:00:00', 1),
(8, UUID(), 'test6', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '2010-01-01 00:00:00', 1),
(9, UUID(), 'test7', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '남자', '2000-01-01 00:00:00', 1),
(10, UUID(), 'test8', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1990-01-01 00:00:00', 1),
(11, UUID(), 'test9', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '남자', '1980-01-01 00:00:00', 1),
(12, UUID(), 'test10', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1988-01-01 00:00:00', 1),
(13, UUID(), 'test11', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '남자', '2010-01-01 00:00:00', 1),
(14, UUID(), 'test12', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '2000-01-01 00:00:00', 1),
(15, UUID(), 'test13', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1990-01-01 00:00:00', 1),
(16, UUID(), 'test14', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1980-01-01 00:00:00', 1),
(17, UUID(), 'test15', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1988-01-01 00:00:00', 1),
(18, UUID(), 'test16', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '2010-01-01 00:00:00', 1),
(19, UUID(), 'test17', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '2000-01-01 00:00:00', 1),
(20, UUID(), 'test18', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1990-01-01 00:00:00', 1),
(21, UUID(), 'test19', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1980-01-01 00:00:00', 1),
(22, UUID(), 'test20', '$2a$10$lKgLMqn1eKgL3bXcRrMBbe5y0Xe0nDvyHMBNqzfO8ZyQccB5v21xW', '여자', '1988-01-01 00:00:00', 1)
;

INSERT INTO `user_auth` (`no`, `username`, `auth`)
VALUES 
(4, 'test1', 'ROLE_USER'),
(5, 'test2', 'ROLE_USER'),
(6, 'test3', 'ROLE_USER'),
(7, 'test4', 'ROLE_USER'),
(8, 'test5', 'ROLE_USER'),
(9, 'test6', 'ROLE_USER'),
(10, 'test7', 'ROLE_USER'),
(11, 'test8', 'ROLE_USER'),
(12, 'test9', 'ROLE_USER'),
(13, 'test10', 'ROLE_USER'),
(14, 'test11', 'ROLE_USER'),
(15, 'test12', 'ROLE_USER'),
(16, 'test13', 'ROLE_USER'),
(17, 'test14', 'ROLE_USER'),
(18, 'test15', 'ROLE_USER'),
(19, 'test16', 'ROLE_USER'),
(20, 'test17', 'ROLE_USER'),
(21, 'test18', 'ROLE_USER'),
(22, 'test19', 'ROLE_USER'),
(23, 'test20', 'ROLE_USER')
;