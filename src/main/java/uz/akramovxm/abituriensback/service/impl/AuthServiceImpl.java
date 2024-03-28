package uz.akramovxm.abituriensback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import uz.akramovxm.abituriensback.dto.request.LoginRequest;
import uz.akramovxm.abituriensback.entity.User;
import uz.akramovxm.abituriensback.security.JWTProvider;
import uz.akramovxm.abituriensback.service.AuthService;
import uz.akramovxm.abituriensback.service.UserService;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private UserService userService;

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
}
