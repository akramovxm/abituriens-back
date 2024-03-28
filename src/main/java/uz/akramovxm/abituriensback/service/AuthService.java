package uz.akramovxm.abituriensback.service;

import uz.akramovxm.abituriensback.dto.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
}
