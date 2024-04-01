package uz.akramovxm.abituriensback.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import uz.akramovxm.abituriensback.entity.User;
import uz.akramovxm.abituriensback.exception.OAuth2AuthenticationProcessingException;
import uz.akramovxm.abituriensback.exception.ResourceNotFoundException;
import uz.akramovxm.abituriensback.security.oauth2.user.OAuth2UserInfo;
import uz.akramovxm.abituriensback.security.oauth2.user.OAuth2UserInfoFactory;
import uz.akramovxm.abituriensback.service.UserService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                userRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );

        if(oAuth2UserInfo.getEmail().isEmpty()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        User user;
        try {
            user = userService.findByEmail(oAuth2UserInfo.getEmail());
            if (user.isLocked()) {
                throw new OAuth2AuthenticationProcessingException("User account is locked");
            }
            user = userService.update(user, userRequest, oAuth2UserInfo);
        } catch (ResourceNotFoundException e) {
            user = userService.create(userRequest, oAuth2UserInfo);
        }

        return UserDetailsImpl.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .locked(user.isLocked())
                .attributes(oAuth2User.getAttributes())
                .build();
    }
}
