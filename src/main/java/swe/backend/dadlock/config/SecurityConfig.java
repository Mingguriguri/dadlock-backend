package swe.backend.dadlock.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import swe.backend.dadlock.filter.CustomLogoutFilter;
import swe.backend.dadlock.filter.JwtAuthenticationFilter;
import swe.backend.dadlock.service.CustomUserDetailsService;
import swe.backend.dadlock.service.JwtUtil;
import swe.backend.dadlock.service.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import swe.backend.dadlock.handler.OAuth2SuccessHandler;
import swe.backend.dadlock.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/oauth2/**", "/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
            "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html", "/reissue",
    }; // 인증 필터를 거치지 않는 경로
    
    private final JwtUtil jwtUtil; // jwtutil 생성자 주입
    private final RedisUtil redisUtil;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        //csrf disable
        http
                .csrf(csrf -> csrf.disable());

        //From 로그인 방식 disable
        http
                .formLogin(formLogin -> formLogin.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic(httpBasic -> httpBasic.disable());

        //경로별 인가 작업
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/join").authenticated()
//                        .anyRequest().permitAll()
//                );
        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated() // 나머지 요청은 모두 인증 필요
                );

        //oauth2
        http
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(oAuth2SuccessHandler)
                );

        http.addFilterBefore(new CustomLogoutFilter(jwtUtil, redisUtil), LogoutFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        //세션 설정
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
