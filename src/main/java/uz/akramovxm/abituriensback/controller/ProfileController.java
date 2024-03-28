package uz.akramovxm.abituriensback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.akramovxm.abituriensback.dto.response.Response;
import uz.akramovxm.abituriensback.dto.view.UserDTO;
import uz.akramovxm.abituriensback.service.UserService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Response getMe(Authentication authentication) {
        UserDTO user = userService.getByEmail(authentication.getName());

        return new Response(HttpStatus.OK.name(), user);
    }
}
