package swe.backend.dadlock.service;

import swe.backend.dadlock.dto.oauth2.JoinGoogleUserDTO;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.entity.Role;
import swe.backend.dadlock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleJoinService {
    private final UserRepository userRepository;

    /**
     * 구글 회원 회원가입 로직
     */

    public boolean joinGoogleProcess(JoinGoogleUserDTO joinGoogleUserDTO) {
        User newUser = setGoogleUserEntity(joinGoogleUserDTO);

        log.info(newUser.toString());

        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }

    /**
     * 회원 엔티티 생성
     */
    private static User setGoogleUserEntity(JoinGoogleUserDTO joinGoogleUserDTO) {
        User newUser = new User();
        newUser.setNickname(joinGoogleUserDTO.getNickname());
        newUser.setGoogleId(joinGoogleUserDTO.getGoogleId());
        newUser.setRecentConnect(LocalDateTime.now());
        newUser.setCreatedAt(LocalDate.now());
        newUser.setRole(Role.valueOf(joinGoogleUserDTO.getRole()));
        newUser.setDeleteAt(null);

        return newUser;
    }


    public void updateGoogleUser(JoinGoogleUserDTO joinGoogleUserDTO) {
        User user = userRepository.findById(joinGoogleUserDTO.getGoogleId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setNickname(joinGoogleUserDTO.getNickname());
        userRepository.save(user);
    }
}
