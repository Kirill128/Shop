package by.itacademy.shop.api.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

        private Long id;

        @Size(min = 2,max=23,message = "Name must have no between 4 and 23 symbols")
        private String name;
        @Email(message = "Wrong email!")
        private String email;
        @Pattern(regexp = "\\+?375\\d{9}",message = "Wrong phone! Must contain 12 numbers")
        private String phone;
        @Size(min = 4, max = 20,message = "Password must have between 4 and 10 symbols")
        private String password;

        private boolean isAdmin;
        private List<OrderDto>  orders;

}
