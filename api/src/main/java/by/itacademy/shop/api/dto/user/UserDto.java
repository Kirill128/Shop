package by.itacademy.shop.api.dto.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private List<RoleDto> roles;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
}
