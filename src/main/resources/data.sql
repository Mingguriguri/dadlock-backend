-- MySQL의 문자 집합 설정 (UTF-8로 인코딩하도록)
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
SET character_set_connection=utf8mb4;

CREATE TABLE IF NOT EXISTS Quiz (
                                    quiz_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    subject ENUM('과학', '경제', '역사', '시사', '영단어', '건강') NOT NULL,
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
                                                                                                                  ('시사', 'MC', 'EZ', '2022년 기준 한국의 고령화 지수(65세 이상 인구비율)는 약?', '25%', '8%', '15%', '25%', '33%', '40%'),
                                                                                                                  ('과학', 'TF', 'MD', '인간의 유전자는 46개의 염색체로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('경제', 'SA', 'HD', '정부가 재정 적자를 메우기 위해 발행하는 것은 무엇인가요?', '국채', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('역사', 'MC', 'HD', '중세 유럽에서 "흑사병"이 대규모로 발생한 시기는 언제인가요?', '14세기', '11세기', '12세기', '13세기', '14세기', '15세기'),
                                                                                                                  ('영단어', 'TF', 'MD', 'Envy는 시기심을 뜻하는 영단어이다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('건강', 'SA', 'EZ', '폐 기능 향상을 위한 운동은?', '조깅', NULL, NULL, NULL, NULL, NULL);