package uz.akramovxm.abituriensback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import uz.akramovxm.abituriensback.dto.view.UserDTO;
import uz.akramovxm.abituriensback.entity.AuthProvider;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.entity.User;
import uz.akramovxm.abituriensback.exception.ResourceExistsException;
import uz.akramovxm.abituriensback.exception.ResourceNotFoundException;
import uz.akramovxm.abituriensback.mapper.UserMapper;
import uz.akramovxm.abituriensback.repository.UserRepository;
import uz.akramovxm.abituriensback.security.oauth2.user.OAuth2UserInfo;
import uz.akramovxm.abituriensback.service.UserService;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {
    private final String RESOURCE_NAME = User.class.getSimpleName();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "email", email)
        );
    }

    @Override
    public UserDTO getByEmail(String email) {
        User user = findByEmail(email);
        return userMapper.toDTO(user);
    }

    @Override
    public User create(String firstName, String lastName, String email, String password, String phoneNumber, LocalDate birthDate, Role role, boolean locked, boolean enabled) {
        if (userRepository.existsByEmail(email)) {
            throw new ResourceExistsException(RESOURCE_NAME, "email", email);
        }
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResourceExistsException(RESOURCE_NAME, "phoneNumber", phoneNumber);
        }

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .role(role)
                .provider(AuthProvider.local)
                .locked(locked)
                .enabled(enabled)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User create(OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = User.builder()
                .firstName(oAuth2UserInfo.getFirstName())
                .lastName(oAuth2UserInfo.getLastName())
                .email(oAuth2UserInfo.getEmail())
                .role(Role.PUPIL)
                .provider(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()))
                .locked(false)
                .enabled(true)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User update(User user, OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo) {
        if (oAuth2UserInfo.getFirstName() != null && !oAuth2UserInfo.getFirstName().isEmpty()) {
            user.setFirstName(oAuth2UserInfo.getFirstName());
        }
        if (oAuth2UserInfo.getLastName() != null && !oAuth2UserInfo.getLastName().isEmpty()) {
            user.setFirstName(oAuth2UserInfo.getLastName());
        }
        user.setProvider(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()));
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
