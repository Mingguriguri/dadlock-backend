-- MySQL의 문자 집합 설정 (UTF-8로 인코딩하도록)
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET character_set_connection=utf8mb4;

CREATE TABLE IF NOT EXISTS Quiz (
                                    quiz_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    subject ENUM('SCIENCE', 'ECONOMY', 'HISTORY', 'PREVIEW', 'ENG_WORD', 'HEALTH') NOT NULL,
                                    type ENUM('MC', 'TF', 'SA') NOT NULL,
                                    level ENUM('EZ', 'MD', 'HD') NOT NULL,
                                    question TEXT NOT NULL,
                                    correctAnswer VARCHAR(255) NOT NULL,
                                    optionA VARCHAR(255),
                                    optionB VARCHAR(255),
                                    optionC VARCHAR(255),
                                    optionD VARCHAR(255),
                                    optionE VARCHAR(255)
);


-- Quiz 테이블에 데이터 삽입 (ENUM 반영)
INSERT INTO Quiz (subject, type, level, question, correctAnswer, optionA, optionB, optionC, optionD, optionE) VALUES
                                                                                                                  ('PREVIEW', 'MC', 'EZ', '2022년 기준 한국의 고령화 지수(65세 이상 인구비율)는 약?', '25%', '8%', '15%', '25%', '33%', '40%'),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '인간의 유전자는 46개의 염색체로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('ECONOMY', 'SA', 'HD', '정부가 재정 적자를 메우기 위해 발행하는 것은 무엇인가요?', '국채', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('HISTORY', 'MC', 'HD', '중세 유럽에서 "흑사병"이 대규모로 발생한 시기는 언제인가요?', '14세기', '11세기', '12세기', '13세기', '14세기', '15세기'),
                                                                                                                  ('ENG_WORD', 'TF', 'MD', 'Envy는 시기심을 뜻하는 영단어이다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('HEALTH', 'SA', 'EZ', '폐 기능 향상을 위한 운동은?', '조깅', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('SCIENCE', 'MC', 'EZ', '태양계에서 가장 큰 행성은?', '목성', '지구', '화성', '금성', '목성', '토성'),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '알루미늄은 철보다 무겁다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '지구의 대기권 중 가장 외부에 있는 층은 무엇인가요?', '외기권', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('ECONOMY', 'MC', 'EZ', '경제학에서 "수요"란 무엇을 의미하는가?', '소비자의 구매 의사', '공급', '시장', '소비자의 구매 의사', '생산자의 판매 의사', '정부의 정책'),
                                                                                                                  ('ECONOMY', 'TF', 'MD', '경제 성장률은 한 나라의 경제가 성장하는 속도를 나타낸다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('ECONOMY', 'SA', 'HD', '주식시장에서 주가의 장기적 상승 추세를 무엇이라 하나요?', '강세장', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('HISTORY', 'MC', 'EZ', '미국의 초대 대통령은 누구인가요?', '조지 워싱턴', '토머스 제퍼슨', '존 애덤스', '조지 워싱턴', '제임스 매디슨', '앤드류 잭슨'),
                                                                                                                  ('HISTORY', 'TF', 'MD', '중국의 진시황은 만리장성을 건설했다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('HISTORY', 'SA', 'HD', '페르시아 전쟁에서 그리스 연합군이 페르시아 군을 물리친 결정적인 전투는 무엇인가요?', '플라타이아 전투', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('PREVIEW', 'MC', 'EZ', '다음 중 2022년 한국의 인구는?', '6천만 명', '5천만 명', '5천5백만 명', '6천만 명', '6천5백만 명', '7천만 명'),
                                                                                                                  ('PREVIEW', 'TF', 'MD', '2050년 경제협력개발기구(OECD) 국가의 평균 노인 부양비는 현재의 2배에 육박할 것이다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('PREVIEW', 'SA', 'HD', '4차 산업혁명 시대 세계 경제를 이끌 주력 산업 분야는?', '데이터, AI, 로보틱스 등', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('ENG_WORD', 'MC', 'EZ', '다음 중 "가족"이라는 뜻의 영단어는?', 'family', 'kids', 'parents', 'family', 'relatives', 'friends'),
                                                                                                                  ('ENG_WORD', 'TF', 'MD', 'Arrogant는 거만한을 뜻하는 영단어이다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('ENG_WORD', 'SA', 'HD', 'Ambiguous의 뜻은?', '모호한', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('HEALTH', 'MC', 'EZ', '비타민 C가 풍부한 과일은?', '오렌지', '사과', '바나나', '오렌지', '망고', '키위'),
                                                                                                                  ('HEALTH', 'TF', 'MD', '혈액 순환을 돕는 아연은 콩에 많이 들어있다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('HEALTH', 'SA', 'HD', '성인 남성의 하루 권장 아연 섭취량은?', '11밀리그램', NULL, NULL, NULL, NULL, NULL);
