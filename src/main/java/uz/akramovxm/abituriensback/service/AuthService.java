package uz.akramovxm.abituriensback.service;

import uz.akramovxm.abituriensback.dto.request.LoginRequest;
import uz.akramovxm.abituriensback.dto.request.RegisterRequest;
import uz.akramovxm.abituriensback.dto.request.ResendCodeRequest;
import uz.akramovxm.abituriensback.dto.request.VerifyRequest;

public interface AuthService {
    String login(LoginRequest request);

    void register(RegisterRequest request);

    void verify(VerifyRequest request);

    void sendCode(ResendCodeRequest request);
}
