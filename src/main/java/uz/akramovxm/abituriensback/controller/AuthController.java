package uz.akramovxm.abituriensback.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.akramovxm.abituriensback.dto.request.LoginRequest;
import uz.akramovxm.abituriensback.dto.response.AuthResponse;
import uz.akramovxm.abituriensback.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);

        return new AuthResponse(token, HttpStatus.OK.name());
    }
}
