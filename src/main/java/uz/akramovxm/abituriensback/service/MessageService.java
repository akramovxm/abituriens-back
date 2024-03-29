package uz.akramovxm.abituriensback.service;

public interface MessageService {
    void sendVerifyCode(String email, String verifyCode);
}
