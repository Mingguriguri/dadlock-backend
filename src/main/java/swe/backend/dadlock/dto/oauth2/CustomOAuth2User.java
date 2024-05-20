package swe.backend.dadlock.dto.oauth2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import swe.backend.dadlock.entity.Role;
import swe.backend.dadlock.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User, UserDetails {

    @Getter
    private String googleId;
    private swe.backend.dadlock.dto.oauth2.JoinGoogleUserDTO joinGoogleUserDTO;

    public CustomOAuth2User(String googleId) {
        this.googleId = googleId;
    }

    public CustomOAuth2User(JoinGoogleUserDTO joinGoogleUserDTO) {
        this.joinGoogleUserDTO = joinGoogleUserDTO;
    }

    public CustomOAuth2User(String googleId, JoinGoogleUserDTO joinGoogleUserDTO) {
        this.googleId = googleId;
        this.joinGoogleUserDTO = joinGoogleUserDTO;
    }

    public static CustomOAuth2User create(User user) {
        return new CustomOAuth2User(
                user.getGoogleId()
        );
    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return joinGoogleUserDTO.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getName() {

        return joinGoogleUserDTO.getGoogleId();
    }

    public String getUsername() {

        return joinGoogleUserDTO.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
