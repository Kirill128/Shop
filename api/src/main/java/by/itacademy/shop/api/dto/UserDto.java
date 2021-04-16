package by.itacademy.shop.api.dto;

import by.itacademy.shop.entities.Order;
import by.itacademy.shop.entities.Role;
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
        private List<Role> roles;
        private List<Order> orders;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    ", roles=" + roles +
                    '}';
        }
}
