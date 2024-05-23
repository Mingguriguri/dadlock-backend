package swe.backend.dadlock.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Subject {

    SCIENCE("과학"),
    ECONOMY("경제"),
    HISTORY("역사"),
    PREVIEW("시사"),
    ENG_WORD("영단어"),
    HEALTH("건강");

    @JsonValue
    private final String value;

    Subject(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Subject from(String param) {
        for (Subject subject : Subject.values()) {
            if (subject.getValue().equals(param)) {
                return subject;
            }
        }
        log.debug("Subject.from() exception occur param: {}", param);
        return null;
    }

}
