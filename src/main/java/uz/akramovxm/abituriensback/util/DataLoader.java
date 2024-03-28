package uz.akramovxm.abituriensback.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        userService.createUser("admin", "admin", "admin@mail.com", "123", Role.ADMIN, false, true);
        userService.createUser("teacher", "teacher", "teacher@mail.com", "123", Role.TEACHER, false, true);
        userService.createUser("pupil", "pupil", "pupil@mail.com", "123", Role.PUPIL, false, true);
    }
}
