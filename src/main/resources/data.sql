CREATE TABLE Quiz (
                      quizId BIGINT AUTO_INCREMENT PRIMARY KEY,
                      subject VARCHAR(255) NOT NULL,
                      type VARCHAR(255) NOT NULL,
                      level VARCHAR(255) NOT NULL,
                      question TEXT NOT NULL,
                      correctAnswer VARCHAR(255) NOT NULL,
                      optionA VARCHAR(255) NOT NULL,
                      optionB VARCHAR(255),
                      optionC VARCHAR(255),
                      optionD VARCHAR(255),
                      optionE VARCHAR(255)
);

CREATE TABLE QuizAttempt (
                             attemptId BIGINT AUTO_INCREMENT PRIMARY KEY,
                             quizId BIGINT,
                             userId BIGINT,
                             isCorrect BOOLEAN,
                             attemptTime DATETIME,
                             FOREIGN KEY (quizId) REFERENCES Quiz(quizId),
                             FOREIGN KEY (userId) REFERENCES User(userId)
);

-- Quiz 테이블에 데이터 삽입
INSERT INTO Quiz (subject, type, level, question, correctAnswer, optionA, optionB, optionC, optionD, optionE) VALUES
                                                                                                                  ('시사', 'MC', 'EZ', 'OECD 국가 중 가장 출산율이 낮은 국가는?', '한국', '한국', '일본', '미국', '프랑스', '독일'),
                                                                                                                  ('과학', 'TF', 'MD', '물은 두 개의 수소 원자와 하나의 산소 원자로 이루어져 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('수학', 'SA', 'HD', 'π의 소수점 이하 첫째 자리는?', '3.1', NULL, NULL, NULL, NULL, NULL);
