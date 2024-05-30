package swe.backend.dadlock.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;
import swe.backend.dadlock.dto.webapp.WebAppResponseDTO;
import swe.backend.dadlock.entity.Role;
import swe.backend.dadlock.entity.Subject;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.WebApp;
import swe.backend.dadlock.exception.NotOwnerException;
import swe.backend.dadlock.repository.UserRepository;
import swe.backend.dadlock.repository.WebAppRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WebAppServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebAppRepository webAppRepository;

    @Autowired
    private WebAppService webAppService;

    private final String USER_GOOGLE_ID = "kim6562166086@gmail.com";

    @BeforeEach
    void beforeEach() {
        // 유저 데이터 넣기
        User user = createUser(USER_GOOGLE_ID, "김민형");
        userRepository.save(user);
    }

    @DisplayName("user, appUrl, timeLimit, subject를 받아 WebApp 엔티티를 생성한다.")
    @Test
    void createWebAppTest() {
        //given
        String appUrl = "https://google.com";
        int timeLimit = 20;
        Subject subject = Subject.from("과학");
        WebAppRequestDTO.CreateDTO requestDTO = createWebAppCreateRequestDTO(appUrl, timeLimit, subject);

        //when
        WebAppResponseDTO.CommonDTO responseDTO = webAppService.createWebApp(USER_GOOGLE_ID, requestDTO);

        //then
        assertThat(responseDTO.getId()).isNotNull();
        assertThat(responseDTO)
                .extracting("appUrl", "timeLimit", "subject")
                .contains(appUrl, timeLimit, subject);
    }

    @DisplayName("해당 유저가 이미 생성한 URL을 생성하려고하면, DuplicateFormatFlagsException이 발생한다.")
    @Test
    void createWebAppDuplicateTest() {
        //given
        String appUrl1 = "https://google.com";
        int timeLimit1 = 20;
        Subject subject1 = Subject.from("과학");

        String appUrl2 = "https://google.com";
        int timeLimit2 = 15;
        Subject subject2 = Subject.from("경제");

        WebAppRequestDTO.CreateDTO requestDTO1 = createWebAppCreateRequestDTO(appUrl1, timeLimit1, subject1);
        WebAppRequestDTO.CreateDTO requestDTO2 = createWebAppCreateRequestDTO(appUrl2, timeLimit2, subject2);

        webAppService.createWebApp(USER_GOOGLE_ID, requestDTO1);

        //when
        Throwable thrown = catchThrowable(() ->
                webAppService.createWebApp(USER_GOOGLE_ID, requestDTO2)
        );

        //then
        // 예외 메시지:  Flags = '이미 존재하는 URL입니다: https://google.com'
        assertThat(thrown)
                .isInstanceOf(DuplicateFormatFlagsException.class)
                        .hasMessageContaining("이미 존재하는 URL입니다: " + requestDTO2.getAppUrl());

//        assertThatThrownBy(() -> {
//            webAppService.createWebApp(USER_GOOGLE_ID, requestDTO2);
//        })
//                .isInstanceOf(DuplicateFormatFlagsException.class)
//                .hasMessageContaining("이미 존재하는 URL입니다: " + requestDTO2.getAppUrl());
    }

    @DisplayName("User의 googleId와 webApp의 id를 통해 webApp 엔티티를 삭제한다.")
    @Test
    void deleteWebAppTest() {
        //given
        String appUrl = "https://google.com";
        int timeLimit = 20;
        Subject subject = Subject.from("과학");

        User findUser = findUserByGoogleId(USER_GOOGLE_ID);
        WebApp webApp = WebApp.create(findUser, appUrl, timeLimit, subject);
        webAppRepository.save(webApp);

        //when
        webAppService.deleteWebApp(findUser.getGoogleId(), webApp.getId());
        Optional<WebApp> findWebApp = webAppRepository.findById(webApp.getId());

        //then
        assertThat(findWebApp)
                .isEmpty();
    }

    @DisplayName("다른 유저의 webApp 엔티티를 삭제하려는 경우 NotOwnerException이 발생한다.")
    @Test
    void deleteWebAppOwnerTest() {
        //given
        String appUrl = "https://google.com";
        int timeLimit = 20;
        Subject subject = Subject.from("과학");
        String otherUserGoogleId = "alsgudtkwjs@gachon.ac.kr";

        User ownerUser = findUserByGoogleId(USER_GOOGLE_ID);
        WebApp webApp = WebApp.create(ownerUser, appUrl, timeLimit, subject);
        webAppRepository.save(webApp);

        //when
        Throwable thrown = catchThrowable(() ->
                webAppService.deleteWebApp(otherUserGoogleId, webApp.getId())
        );

        //then
        assertThat(thrown)
                .isInstanceOf(NotOwnerException.class)
                .hasMessage("다른 회원의 WebAppURL입니다. URL = " + webApp.getAppUrl());
    }

    @DisplayName("User의 googleId에 해당하는 WebApp 엔티티들을 responseDTO의 형태로 조회한다")
    @Test
    void getUrlListTest() {
        //given
        String appUrl1 = "https://google.com";
        int timeLimit1 = 20;
        Subject subject1 = Subject.from("과학");

        String appUrl2 = "https://naver.com";
        int timeLimit2 = 15;
        Subject subject2 = Subject.from("경제");

        User findUser = findUserByGoogleId(USER_GOOGLE_ID);
        WebApp webApp1 = WebApp.create(findUser, appUrl1, timeLimit1, subject1);
        WebApp webApp2 = WebApp.create(findUser, appUrl2, timeLimit2, subject2);

        webAppRepository.saveAll(List.of(webApp1, webApp2));

        //when
        List<WebAppResponseDTO.CommonDTO> responseDTOList = webAppService.getUrlList(USER_GOOGLE_ID);

        //then
        assertThat(responseDTOList.get(0).getId()).isNotNull();
        assertThat(responseDTOList.get(1).getId()).isNotNull();
        assertThat(responseDTOList)
                .hasSize(2)
                .extracting("appUrl", "timeLimit", "subject")
                .containsExactlyInAnyOrder(
                        tuple(appUrl1, timeLimit1, subject1),
                        tuple(appUrl2, timeLimit2, subject2)
                );
    }

    private User findUserByGoogleId(String userGoogleId) {
        return userRepository.findById(userGoogleId)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다. googleId = " + userGoogleId));
    }

    private User createUser(String googleId, String nickname) {
        return User.builder()
                .googleId(googleId)
                .nickname(nickname)
                .recentConnect(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .createdAt(LocalDate.now())
                .build();
    }

    private static WebAppRequestDTO.CreateDTO createWebAppCreateRequestDTO(String appUrl, int timeLimit, Subject subject) {
        WebAppRequestDTO.CreateDTO requestDTO = WebAppRequestDTO.CreateDTO.builder()
                .appUrl(appUrl)
                .timeLimit(timeLimit)
                .subject(subject)
                .build();
        return requestDTO;
    }

}