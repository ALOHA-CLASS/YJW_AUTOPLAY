-- Insert statements for new movies into the movies table

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