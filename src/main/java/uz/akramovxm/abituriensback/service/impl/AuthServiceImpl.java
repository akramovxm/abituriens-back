package uz.akramovxm.abituriensback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.akramovxm.abituriensback.dto.request.LoginRequest;
import uz.akramovxm.abituriensback.dto.request.RegisterRequest;
import uz.akramovxm.abituriensback.dto.request.ResendCodeRequest;
import uz.akramovxm.abituriensback.dto.request.VerifyRequest;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.entity.User;
import uz.akramovxm.abituriensback.security.JWTProvider;
import uz.akramovxm.abituriensback.service.AuthService;
import uz.akramovxm.abituriensback.service.MessageService;
import uz.akramovxm.abituriensback.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    private final Map<String, String> verifyCodes = new HashMap<>();

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @Override
    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userService.findByEmail(request.getEmail());

        Map<String, Object> claims = Map.of(
                "authorities", user.getAuthorities(),
                "role", user.getRole()
        );

        return jwtProvider.generateToken(claims, user.getUsername());
    }

    @Transactional
    @Override
    public void register(RegisterRequest request) {
        userService.createUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getBirthDate(),
                Role.PUPIL,
                false,
                false
        );

        sendCode(new ResendCodeRequest(request.getEmail()));
    }

    @Override
    public void verify(VerifyRequest request) {
        String verifyCode = verifyCodes.get(request.getEmail());
        User user = userService.findByEmail(request.getEmail());

        if (verifyCode == null || !verifyCode.equals(request.getVerifyCode())) {
            throw new MailAuthenticationException("The verification code is invalid");
        }

        verifyCodes.remove(request.getEmail());

        user.setEnabled(true);

        userService.save(user);
    }

    @Override
    public void sendCode(ResendCodeRequest request) {
        Random random = new Random();
        String verifyCode = String.format("%04d", random.nextInt(10000));

        verifyCodes.put(request.getEmail(), verifyCode);

        messageService.sendVerifyCode(request.getEmail(), verifyCode);
    }
}
