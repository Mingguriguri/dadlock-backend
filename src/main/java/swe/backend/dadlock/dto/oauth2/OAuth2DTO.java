package swe.backend.dadlock.dto.oauth2;

public interface OAuth2DTO {

    //제공자 (Ex. naver, google, ...)
    String getProvider();

    //제공자에서 발급해주는 아이디(번호)
    String getId();

    //사용자 실명 (설정한 이름)
    String getName();
}
