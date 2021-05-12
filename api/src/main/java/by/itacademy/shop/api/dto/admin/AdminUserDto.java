package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.api.dto.user.RoleDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDto {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private List<RoleDto> roles;

}
