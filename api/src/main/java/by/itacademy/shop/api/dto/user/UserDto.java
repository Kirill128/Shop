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
        private boolean isAdmin;
        private List<OrderDto>  orders;

}
