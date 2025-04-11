-- Active: 1741423366434@@127.0.0.1@3306@autoplay
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


-- 컨텐츠
TRUNCATE TABLE movies;
INSERT INTO movies (`id`, `title`, `content`, `type`, `url`, `player_id`, `full_id`, `img`, `seq`) VALUES
(UUID(), '오늘의 파스타', '우주는 석현과 연애한지 2년을 기념하기 위해 집에서 파스타를 해준다. 석현의 마음과 우주의 마음 그 둘 사이의 거리와 온도 모든게 다르다.', '저시각', 'https://youtu.be/Dn5Y9YAT3fw', 'Dn5Y9YAT3fw', 'OH0jcElW3vU', '/img/thumb/low/오늘의파스타.jpg', 1),
(UUID(), '외면받는 유기동물', '반려동물 인구 천만 시대. 한 해 버려지는 유기동물 약 12만 마리.새삶을 찾는 유기동물은 절반도 넘지 못한다.사랑이 필요한 유기동물을 어떻게 도와줄 수 있을까? 작은 관심으로 시작된 변화는 더이상 유기동물이 늘어나지 않는 세상을 만들 것이다.', '저시각', 'https://youtu.be/er7jbCCmVPE', 'er7jbCCmVPE', 'hVvlNfsyduE', '/img/thumb/low/외면받는유기동물.png', 2),
(UUID(), '불량주리', '프랑스의 아늑한 마을 카스티요네(Castillonnès) 에 있는 빵집에서 영국인 관광객을 영원히 몰아내려는 계획을 세운다. 하지만 제빵사와 한 영국 남성 사이에서 싹트는 여름 로맨스가 예상치 못한 변화를 가져오며, 뜻밖의 인물이 중심에 서게 된다.', '저시각', 'https://youtu.be/F-cUPrLQJ1s', 'F-cUPrLQJ1s', 'QJc9mMm4TQA', '/img/thumb/low/불량주리.png', 3),
(UUID(), '은빛', '자식들을 모두 결혼시키고, 단 둘 뿐인 집. 이 노부부들은 서로의 일상을 숨긴 채 각자의 삶을 살아간다. 아내 모르게 지하철 택배 일을 하는 할아버지와 남편의 생일을 잊은 할머니의 하루.', '저시각', 'https://youtu.be/uZmdsCHR11E', 'uZmdsCHR11E', 'gHWZzM5qUhE', '/img/thumb/low/은빛.png', 4),
(UUID(), '순환버스도 밤에는 차고지로 간다', '상우는 우연히 순환버스를 타게 된다. 그리고 창밖으로 자신의 모습들을 보게 된다.', '저시각', 'https://youtu.be/uGAhXyaBR2Y', 'uGAhXyaBR2Y', 'cy3On370FuM', '/img/thumb/low/순환버스도밤에는차고지로간다.jpg', 5),
(UUID(), '유쾌한 황당', '직장 상사에게 갖은 욕을 들어 먹고 자존감이 바닥을 친 한 사내의 소소한 일탈. 그래봤자, 외근 핑계로 하루 땡땡이를 치는 정도의 소심한 사내의 일탈이지만, 그 와중에 우연히 들어가게 된 비좁은 카페에서 그간 이 사내가 잊고 살았던 가치를 찾게 되는데..', '저시각', 'https://youtu.be/q2v4mHkNjnA', 'q2v4mHkNjnA', 'KgxX6Gu19aY', '/img/thumb/low/유쾌한황당.png', 6),
(UUID(), '비온 뒤 차차', '철거 전문 일을 하며 성실하게 살아가는 신우와 디자인 회사의 촉망받는 인재였던 수정, 어느 날 전 직장 선배를 통해 소개팅 기회가 찾아오고 설레는 마음으로 소개팅에 나가게 되는데...', '저시각', 'https://youtu.be/Zwidok1WsbI', 'Zwidok1WsbI', 'iXHBEW-MLn4', '/img/thumb/low/비온뒤차차.jpeg', 7),
(UUID(), '히키코모루', '그녀의 일상은 규칙적이고 통제된 상태로 진행된다. 그녀는 무언가로 부터 도망치고 방안에 틀어 박혔다. 완벽하고 싶었던 그녀가 도망친 것은 소중했던 관계 그리고 절실 했었던 꿈이다. 하지만 집안으로 스며 들어오는 사소한 자극은 그녀가 도망친 것들을 다시 생각나게 한다.', '저시각', 'https://youtu.be/RTROgAP5jP0', 'RTROgAP5jP0', 'hr5d1jR1IeA', '/img/thumb/low/히키코모루.jpg', 8),
(UUID(), '거리의 연기자', '거장 감독의 작품 오디션 연락을 받은 태희. 오랜만의 오디션이라 설레는 기대감으로 오디션에 나서지만 모든 것이 예상 밖이다.기대했던 감독과의 만남도 없고, 사무실이나 연습실이 아닌 길거리에서 연기를 해보라고 하는 조감독. 이 모든 상황이 당황스러운 태희.', '저시각', 'https://youtu.be/gW1bCuahX-M', 'gW1bCuahX-M', 'mGVTnIaFRlg', '/img/thumb/low/거리의연기자.png', 9);

INSERT INTO movies (`id`, `title`, `content`, `type`, `url`, `player_id`, `full_id`, `img`, `seq`) VALUES
(UUID(), '전역하는날', '군인인 선호가 전역을 하는 날 알수없는 바이러스가 전세계에 발생하여 사람들이 좀비로 변하게 된다. 선호는 자신의 어머니를 구하기 위해 집에 도착하지만 이미 엄마 또한 바이러스에 감염되어 선호를 공격한다. 결국 선호는 자신에 손으로 엄마를 죽인후 괴로워 하던 중 선호 또한 공격 당한 상처로 바이러스에 감염되는데...', '고시각', 'https://youtu.be/860CYYKMqms', '860CYYKMqms', 'bT5WrtFvVJ0', '/img/thumb/high/전역하는날.jpg', 1),
(UUID(), '클로즈드', '전쟁상황 같은 알 수 없는 큰 난리에서 급히 도망쳐 외부와 연락도 상황도 끊어진 채 지하 창고 같은 방에 숨게 된 4명의 여학생들의 생존 분투기.', '고시각', 'https://youtu.be/hxtIB159h-M', 'hxtIB159h-M', 'c4S_lJsl3Sc', '/img/thumb/high/클로즈드.jpg', 2),
(UUID(), '윌리 빙엄의 경우', '흉악범들을 대상으로 새로운 형벌이 도입되었다. 그들을 천천히, 고통스럽게 죽음으로 이끄는 신체절단형. 피해자가 원할 때 마다 몇 년에 걸쳐 죄수의 신체 일부가 하나씩 제거된다. 윌리 빙엄의 경우도 예외일 수 없다.', '고시각', 'https://youtu.be/97pMQ6barhw', '97pMQ6barhw', '97pMQ6barhw', '/img/thumb/high/윌리빙엄의경우.png', 3),
(UUID(), '로스트', '유실물보관소로 돈이 가득 든 가방이 들어왔다. 돈가방을 보고 욕심에 사로잡힌 지태는 거짓말, 폭력, 살인, 유기를 저지르게 되는데…지태는 지하철을 타고 홀연히 사람들 속에 섞인다.', '고시각', 'https://youtu.be/igQq_VrhTzk', 'igQq_VrhTzk', 'CAZFcb8R4vA', '/img/thumb/high/로스트.jpg', 4),
(UUID(), '프레데터 다크에이지', '중세 시대의 어느 마을에 정체불명의 흉폭한 약탈자가 출현하여 마을 주민을 공포의 도가니로 몰아가자 겁에 질린 마을 주민들이 추기경에게 약탈자의 축출을 청원하기에 이르고 청원에 대한 기부금이 주된 목적이였던 추기경은 토마스 경이 이끄는 5인조 템플 나이츠를 소집하여 과저에 약탈자와 조우한 경험이 있는 무슬린 출신의 사라센인 사이에드를 길 안내자로 하여 약탈자를 처단하기 위한 사냥의 길을 떠나는데...', '고시각', 'https://youtu.be/scYn-1JcLbM', 'scYn-1JcLbM', '0Tb8IylgwU0', '/img/thumb/high/프레데터다크에이지.jpg', 5),
(UUID(), '영원한 뱀파이어', '큰 시련과 도전속에서 뱀파이어 세계와 현실에서의 비극적 사랑이야기. 헌터와 뱀파이어 사이에 전쟁, 당신은 영원한 삶과 사랑중 어느 쪽을 선택할 것인가?', '고시각', 'https://youtu.be/fybhNSfKTbw', 'fybhNSfKTbw', 'gqZAkHHnoLo', '/img/thumb/high/영원한뱀파이어.png', 6),
(UUID(), '세상이 망했으면 좋겠다', '‘그냥 다 망해버렸으면 좋겠다.’ 라는 말을 입에 달고 사는 균. 그는 사회 부적응 자, 즉 흔히 말하는 히키모코리 이다. 취업은커녕 사회생활 조차 엄두도 내지 못한다. 밖에 나가는 것을 극도로 꺼려 집에서만 생활하는 균. 부모님이 보내준 생활비로 그럭저럭 생활한다. 그러던 어느 날 균은 바깥세상이 심상치 않게 돌아가고 있음을 느끼는데...', '고시각', 'https://youtu.be/H-dn-3tLxVE', 'H-dn-3tLxVE', 'DPleLUDfs88', '/img/thumb/high/세상이.png', 7),
(UUID(), '인형', '자신을 제외한 모든 사람들의 얼굴이 다 똑같이 생긴 세상 속에서 홀로 소외감을 느끼는 소녀, 자신의 절친한 친구마저 똑같은 얼굴로 변해버리자 극단적인 선택을 한다.', '고시각', 'https://youtu.be/Ohm6WAaCjCk', 'Ohm6WAaCjCk', 'DlgQD4t3eg0', '/img/thumb/high/인형.png', 8),
(UUID(), '시간 에이전트', '범죄를 예방하기 위해 미래에서 온 에이전트. 미래로 다시 돌아가려면 그냥 기다리는 방법 밖에 없다. 기다리는 동안 그는 바깥세상과 단절되어 살아야한다. 혼자 있는것에 익숙해진 에이전트는 어느날 다리에서 뛰어내리려는 소녀와 마주치는데...', '고시각', 'https://youtu.be/qyrmJ6aWX1o', 'qyrmJ6aWX1o', 'ahaztzBVGXY', '/img/thumb/high/시간에이전트.jpg', 9);