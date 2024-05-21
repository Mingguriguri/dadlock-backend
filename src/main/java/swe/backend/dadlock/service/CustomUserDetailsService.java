package swe.backend.dadlock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import swe.backend.dadlock.dto.oauth2.CustomOAuth2User;
import swe.backend.dadlock.dto.oauth2.JoinGoogleUserDTO;
import swe.backend.dadlock.entity.User;
import swe.backend.dadlock.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String googleId) throws UsernameNotFoundException {
        User user = userRepository.findById(googleId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        JoinGoogleUserDTO joinGoogleUserDTO = new JoinGoogleUserDTO(
                user.getGoogleId(), user.getNickname(), user.getRole().name());
        return new CustomOAuth2User(null, joinGoogleUserDTO);
    }
}
