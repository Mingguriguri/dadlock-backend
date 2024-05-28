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


-- Quiz 테이블에 데이터 삽입
-- 과학
INSERT INTO Quiz (subject, type, level, question, correctAnswer, optionA, optionB, optionC, optionD, optionE) VALUES
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '태양계에서 가장 큰 행성은?', '목성', '지구', '화성', '금성', '목성', '토성'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '빛의 속도는 약 몇 km/s인가?', '300,000 km/s', '30,000 km/s', '300,000 km/s', '3,000 km/s', '300 km/s', '30 km/s'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '식물의 광합성에 필요한 기체는?', '이산화탄소', '산소', '이산화탄소', '질소', '수소', '메탄'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '원자번호가 1인 원소는?', '수소', '산소', '헬륨', '수소', '탄소', '질소'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '우리 눈에 보이는 빛의 파장 범위는 대략 얼마인가?', '400-700 nm', '300-400 nm', '400-700 nm', '700-1000 nm', '100-300 nm', '10-100 nm'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '대기압의 단위로 흔히 사용되는 것은?', '파스칼', '킬로그램', '미터', '파스칼', '리터', '암페어'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '물체가 물에 뜨는 이유는 무엇 때문인가요?', '밀도', '밀도', '온도', '색깔', '질량', '부피'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '전기의 단위는?', '암페어', '볼트', '암페어', '옴', '와트', '킬로그램'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '식물의 세포벽을 구성하는 물질은?', '셀룰로오스', '셀룰로오스', '키틴', '단백질', '지질', '전분'),
                                                                                                                  ('SCIENCE', 'MC', 'EZ', '인간의 체온을 조절하는 기관은?', '피부', '간', '심장', '피부', '폐', '신장'),

                                                                                                                  ('SCIENCE', 'MC', 'MD', '원소 주기율표에서 6번째 원소는?', '탄소', '산소', '탄소', '질소', '수소', '헬륨'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '중력 가속도의 값은 약 몇 m/s²인가?', '9.8 m/s²', '9.8 m/s²', '10 m/s²', '8.9 m/s²', '9.1 m/s²', '10.5 m/s²'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '지구의 핵이 주로 구성된 두 가지 원소는?', '철과 니켈', '철과 니켈', '철과 구리', '니켈과 아연', '철과 마그네슘', '구리와 아연'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '광합성 과정에서 생성되는 산소는 어디에서 유래하는가?', '물', '이산화탄소', '물', '포도당', '빛', '토양'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '지구에서 가장 흔한 금속은?', '철', '금', '철', '알루미늄', '구리', '은'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '원자의 중심에 위치한 입자는?', '양성자', '전자', '양성자', '중성자', '중성미자', '쿼크'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '지구의 대기는 몇 개의 주요 층으로 나뉘어 있나요?', '5개', '2개', '3개', '4개', '5개', '6개'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '포유류의 폐에서 일어나는 가스 교환은 주로 어디서 이루어지나요?', '폐포', '기관지', '기관', '폐포', '기관지 끝', '폐동맥'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '아이작 뉴턴이 제시한 운동 법칙 중 첫 번째 법칙은?', '관성의 법칙', '가속도의 법칙', '작용-반작용 법칙', '관성의 법칙', '만유인력의 법칙', '상대성 법칙'),
                                                                                                                  ('SCIENCE', 'MC', 'MD', '태양계에서 해왕성의 위성 중 가장 큰 것은?', '트리톤', '타이탄', '이오', '트리톤', '가니메데', '칼리스토'),

                                                                                                                  ('SCIENCE', 'MC', 'HD', '다이아몬드의 결정 구조는?', '면심입방구조', '체심입방구조', '면심입방구조', '육방밀집구조', '정육면체구조', '사방정계구조'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '일반상대성 이론에서 질량이 시공간을 어떻게 변화시키는가?', '질량은 시공간을 휘게 한다', '질량은 시공간을 늘린다', '질량은 시공간을 휘게 한다', '질량은 시공간을 줄인다', '질량은 시공간을 변형시키지 않는다', '질량은 시공간을 회전시킨다'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '양자역학에서 파동함수의 절대값 제곱은 무엇을 나타내는가?', '입자의 위치 확률 밀도', '입자의 에너지', '입자의 운동량', '입자의 위치 확률 밀도', '입자의 스핀', '입자의 전하'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', 'DNA 복제에서 DNA 가닥을 분리하는 효소는?', '헬리케이스', 'DNA 리가아제', 'DNA 폴리메라제', '헬리케이스', '프라이메이스', '톱오이소머라제'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '전자기파 스펙트럼에서 파장이 가장 짧은 것은?', '감마선', '가시광선', '자외선', '적외선', '감마선', 'X선'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '미생물학에서 \'그람 염색\'은 무엇을 분류하는데 사용되는가?', '박테리아', '바이러스', '균류', '박테리아', '원생생물', '효소'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '천문학에서 중성자별이 형성되는 주된 이유는?', '초신성 폭발', '초신성 폭발', '블랙홀 형성', '항성의 붕괴', '항성의 진화', '행성의 충돌'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '양자역학에서 불확정성 원리는 누구에 의해 제안되었는가?', '하이젠베르크', '아인슈타인', '슈뢰딩거', '하이젠베르크', '보어', '디랙'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '비타민 C의 화학 명칭은?', '아스코르빈산', '리보플라빈', '나이아신', '아스코르빈산', '피리독신', '티아민'),
                                                                                                                  ('SCIENCE', 'MC', 'HD', '원자핵을 구성하는 기본 입자는?', '양성자와 중성자', '전자', '양성자', '중성자', '양성자와 중성자', '양성자와 전자'),

                                                                                                                  ('SCIENCE', 'TF', 'EZ', '지구의 자전축은 약 23.5도 기울어져 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '태양은 주로 수소와 헬륨으로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '전기는 전자의 이동으로 발생한다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '다이아몬드는 순수한 탄소로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '지구의 핵은 주로 철과 니켈로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '지구의 자전 속도는 적도에서 가장 빠르다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '모든 금속은 상온에서 고체 상태이다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '인간의 골격은 약 206개의 뼈로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '우리가 마시는 물의 대부분은 순수한 H2O이다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'EZ', '철은 자성을 띄는 금속이다.', 'True', 'True', 'False', NULL, NULL, NULL),

                                                                                                                  ('SCIENCE', 'TF', 'MD', 'DNA는 세포의 핵 안에 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '플루토늄은 자연적으로 발생하는 원소이다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '바람은 고기압에서 저기압으로 불어간다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '헬륨은 가장 가벼운 원소이다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '기체는 액체보다 밀도가 높다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '유전 정보는 RNA에서 DNA로 전달된다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '대류는 액체와 기체에서만 발생한다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '화산활동은 판구조론과 관련이 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '열은 항상 차가운 물체에서 뜨거운 물체로 이동한다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'MD', '염산은 강한 염기이다.', 'False', 'True', 'False', NULL, NULL, NULL),

                                                                                                                  ('SCIENCE', 'TF', 'HD', '초전도체는 절대 영도에서 무한 저항을 가진다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '암흑 물질은 전자기파를 방출하거나 흡수하지 않는다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '상대성이론에 따르면, 빛의 속도는 진공에서만 일정하다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '양자 역학에서는 입자의 정확한 위치와 운동량을 동시에 측정할 수 없다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '중성미자는 매우 높은 질량을 가진 입자이다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '지구의 핵은 고체와 액체로 구성되어 있다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '양자 터널링은 입자가 장벽을 넘어갈 확률을 가진 현상이다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', 'RNA는 유전 정보의 저장을 주된 기능으로 한다.', 'False', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '엔트로피는 시스템의 무질서도를 나타내는 척도이다.', 'True', 'True', 'False', NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'TF', 'HD', '플라즈마는 물질의 네 번째 상태이다.', 'True', 'True', 'False', NULL, NULL, NULL),

                                                                                                                  ('SCIENCE', 'SA', 'EZ', '기체를 액체로 변하게 하는 과정을 무엇이라고 하나요?', '응결', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '식물에서 물과 영양분을 흡수하는 부분은 무엇인가요?', '뿌리', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '컴퓨터의 중앙 처리 장치를 무엇이라고 하나요?', 'CPU', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '전구에서 빛을 내는 부분을 무엇이라고 하나요?', '필라멘트', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '우리 몸의 70% 이상을 차지하는 것은 무엇인가요?', '물', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '햇빛 속의 자외선은 피부에 어떤 비타민을 생성하게 하나요?', '비타민 D', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '사람의 몸에서 이산화탄소를 배출하는 기관은 무엇인가요?', '폐', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '바닷물은 주로 어떤 물질로 인해 짠맛을 내나요?', '소금', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '사람의 몸에서 산소를 운반하는 세포는 무엇인가요?', '적혈구', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'EZ', '지구의 가장 바깥쪽 층을 무엇이라고 하나요?', '지각', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('SCIENCE', 'SA', 'MD', '태양의 주 에너지원은 무엇인가요?', '핵융합', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '기체를 액체로 변환하는 과정을 무엇이라 하나요?', '응축', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '전지에서 화학 에너지를 전기 에너지로 변환하는 장치는 무엇인가요?', '배터리', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '지구의 표면에서 가장 많은 물질 상태는 무엇인가요?', '액체', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '컴퓨터에서 정보를 일시적으로 저장하는 장치는 무엇인가요?', 'RAM (램)', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '자동차에서 연료를 연소시켜 동력을 얻는 장치는 무엇인가요?', '엔진', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '식물의 광합성에 필요한 빛의 주된 원천은 무엇인가요?', '태양', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '음식물의 소화와 영양소 흡수를 주로 담당하는 기관은 무엇인가요?', '소장', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '지구의 대기를 구성하는 주요 층 중 하나는 무엇인가요?', '성층권', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'MD', '지구에서 가장 깊은 해양은 무엇인가요?', '마리아나 해구', NULL, NULL, NULL, NULL, NULL),

                                                                                                                  ('SCIENCE', 'SA', 'HD', '지구의 대기권 중 가장 외부에 있는 층은 무엇인가요?', '외기권', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '세포막을 구성하는 주된 물질은 무엇인가요?', '인지질', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '탄수화물을 단당류로 분해하는 효소는 무엇인가요?', '아밀라', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '화학 반응에서 반응 속도를 높이는 물질은 무엇인가요?', '촉매', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '행성의 궤도를 설명하는 법칙을 제안한 과학자는 누구인가요?', '케플러', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '음극선 관 실험을 통해 전자를 발견한 과학자는 누구인가요?', '톰슨', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '양자역학의 불확정성 원리를 제안한 과학자는 누구인가요?', '하이젠베르크', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '원자의 중심에 위치한 양성자와 중성자의 집합체를 무엇이라 하나요?', '원자핵', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '상대성이론을 제안한 과학자는 누구인가요?', '아인슈타인', NULL, NULL, NULL, NULL, NULL),
                                                                                                                  ('SCIENCE', 'SA', 'HD', '기체의 부피와 온도 관계를 설명하는 법칙은 무엇인가요?', '샤를의 법칙', NULL, NULL, NULL, NULL, NULL);

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
