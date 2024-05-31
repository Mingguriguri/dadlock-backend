package swe.backend.dadlock.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import swe.backend.dadlock.dto.webapp.WebAppRequestDTO;
import swe.backend.dadlock.entity.Role;
import swe.backend.dadlock.entity.Subject;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.WebApp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class WebAppRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebAppRepository webAppRepository;

    private final String USER_GOOGLE_ID = "kim6562166086@gmail.com";

    @BeforeEach
    void beforeEach() {
        // 유저 데이터 넣기
        User user = createUser(USER_GOOGLE_ID, "김민형");
        userRepository.save(user);
    }

    @DisplayName("User의 googleId에 해당하는 WebApp 엔티티들을 리스트 형태로 조회한다.")
    @Test
    void findWebAppListByGoogleIdTest() {
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
        List<WebApp> webAppList = webAppRepository.findWebAppListByGoogleId(USER_GOOGLE_ID);

        //then
        assertThat(webAppList)
                .hasSize(2)
                .extracting("appUrl", "timeLimit", "subject")
                .containsExactlyInAnyOrder(
                        tuple(appUrl1, timeLimit1, subject1),
                        tuple(appUrl2, timeLimit2, subject2)
                );
    }

    @DisplayName("User의 googleId와 appUrl에 해당하는 WebApp 엔티티를 조회한다")
    @Test
    void findWebAppByUserGoogleIdAndAppUrlTest() {
        //given
        String appUrl = "https://google.com";
        int timeLimit = 20;
        Subject subject = Subject.from("과학");

        User findUser = findUserByGoogleId(USER_GOOGLE_ID);
        WebApp webApp = WebApp.create(findUser, appUrl, timeLimit, subject);

        webAppRepository.save(webApp);

        //when
        Optional<WebApp> findWebApp = webAppRepository.findWebAppByUserGoogleIdAndAppUrl(USER_GOOGLE_ID, appUrl);

        //then
        assertThat(findWebApp)
                .isPresent()
                .contains(webApp); // 여기서 webApp 객체가 Optional 내부에 포함되어 있는지 검증
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

    private static WebAppRequestDTO.CreateDTO createWebAppCreateRequestDTO(String appUrl, int timeLimit, String subject) {
        WebAppRequestDTO.CreateDTO requestDTO = WebAppRequestDTO.CreateDTO.builder()
                .appUrl(appUrl)
                .timeLimit(timeLimit)
                .subject(Subject.from(subject))
                .build();
        return requestDTO;
    }

}