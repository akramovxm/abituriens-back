package uz.akramovxm.abituriensback.mapper;

import org.springframework.stereotype.Component;
import uz.akramovxm.abituriensback.dto.view.UserDTO;
import uz.akramovxm.abituriensback.entity.User;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .build();
    }
}
