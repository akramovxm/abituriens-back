package uz.akramovxm.abituriensback.service;

import uz.akramovxm.abituriensback.dto.view.UserDTO;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.entity.User;

public interface UserService {
    User findByEmail(String email);
    UserDTO getByEmail(String email);
    User createUser(String firstName, String lastName, String email, String password, Role role, boolean locked, boolean enabled);
}
