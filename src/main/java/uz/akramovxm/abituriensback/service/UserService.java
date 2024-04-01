package uz.akramovxm.abituriensback.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import uz.akramovxm.abituriensback.dto.view.UserDTO;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.entity.User;
import uz.akramovxm.abituriensback.exception.ResourceNotFoundException;
import uz.akramovxm.abituriensback.security.oauth2.user.OAuth2UserInfo;

import java.time.LocalDate;

public interface UserService {
    User findByEmail(String email) throws ResourceNotFoundException;
    UserDTO getByEmail(String email);
    User create(String firstName, String lastName, String email, String password, String phoneNumber, LocalDate birthDate, Role role, boolean locked, boolean enabled);
    User create(OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo);
    User update(User user, OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo);
    User save(User user);
}
