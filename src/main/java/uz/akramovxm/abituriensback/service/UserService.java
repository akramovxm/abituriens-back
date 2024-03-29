package uz.akramovxm.abituriensback.service;

import uz.akramovxm.abituriensback.dto.view.UserDTO;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.entity.User;

import java.time.LocalDate;

public interface UserService {
    User findByEmail(String email);
    UserDTO getByEmail(String email);
    User createUser(String firstName, String lastName, String email, String password, String phoneNumber, LocalDate birthDate, Role role, boolean locked, boolean enabled);

    User save(User user);
}
