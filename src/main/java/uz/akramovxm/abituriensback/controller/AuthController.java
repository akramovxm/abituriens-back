package uz.akramovxm.abituriensback.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.akramovxm.abituriensback.dto.request.LoginRequest;
import uz.akramovxm.abituriensback.dto.request.RegisterRequest;
import uz.akramovxm.abituriensback.dto.request.ResendCodeRequest;
import uz.akramovxm.abituriensback.dto.request.VerifyRequest;
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);

        return new AuthResponse("Registration completed successfully. Check and verify your email");
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse verify(@Valid @RequestBody VerifyRequest request) {
        authService.verify(request);

        return new AuthResponse("Verification completed successfully. Please login");
    }

    @PostMapping("/resend-code")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse resendCode(@Valid @RequestBody ResendCodeRequest request) {
        authService.sendCode(request);

        return new AuthResponse("The verification code has been re-sent. Check your mail");
    }
}
