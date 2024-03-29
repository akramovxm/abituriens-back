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
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .role(user.getRole().name())
                .build();
    }
}
