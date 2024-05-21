package swe.backend.dadlock.handler;

import swe.backend.dadlock.service.JwtUtil;
import swe.backend.dadlock.service.RedisUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        log.info("CustomOAuth2User: {}", customOAuth2User);

        String googleId = customOAuth2User.getGoogleId();
        log.info("googleId {}",googleId);

        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                "google", authentication.getName());
        String googleAccessToken = client.getAccessToken().getTokenValue();

        log.info("Google Access Token: {}", googleAccessToken); // 로그 추가: 구글 액세스 토큰 확인

        redisUtil.setData(googleId, googleAccessToken);


        // jwt 처리
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String accessToken = jwtUtil.createToken("access", googleId, role, 1000*60*60L); // access token 생성 유효기간 1시간
        String refreshToken = jwtUtil.createToken("refresh", googleId, role, 1000*60*60*24L); // refresh token 생성 유효기간 24시간

        log.info("Access Token: {}", accessToken); // 로그 추가: 액세스 토큰 확인
        log.info("Refresh Token: {}", refreshToken); // 로그 추가: 리프레시 토큰 확인

        redisUtil.setData(accessToken, refreshToken); // 레디스에 리프레시 토큰 저장

        response.addHeader("Authorization", "Bearer " + accessToken); // access token은 Authorization 헤더에
        response.addCookie(createCookie(refreshToken)); // refresh token은 쿠키에
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write("Social_LOGIN_SUCCESS");

        // 세션에 사용자 정보 저장
        request.getSession().setAttribute("user", customOAuth2User);
    }

    private Cookie createCookie(String value) {

        Cookie cookie = new Cookie("refresh", value);
        cookie.setMaxAge(24*60*60); // 쿠키 유효시간 24시간으로 설정
        cookie.setHttpOnly(true); // XSS 공격 방어
        cookie.setSecure(true); // https 옵션 설정
        cookie.setPath("/"); // 모든 곳에서 쿠키 열람이 가능하도록 설정

        return cookie;
    }
}
