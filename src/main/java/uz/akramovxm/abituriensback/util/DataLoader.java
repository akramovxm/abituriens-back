package uz.akramovxm.abituriensback.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.akramovxm.abituriensback.entity.Role;
import uz.akramovxm.abituriensback.service.UserService;

import java.time.LocalDate;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        userService.create(
                "admin",
                "admin",
                "admin@mail.com",
                "123",
                "998909118611",
                LocalDate.now(),
                Role.ADMIN,
                false,
                true
        );
        userService.create(
                "teacher",
                "teacher",
                "teacher@mail.com",
                "123",
                "998909118612",
                LocalDate.now(),
                Role.TEACHER,
                false,
                true
        );
        userService.create(
                "pupil",
                "pupil",
                "pupil@mail.com",
                "123",
                "998909118613",
                LocalDate.now(),
                Role.PUPIL,
                false,
                true
        );
    }
}
