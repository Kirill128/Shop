package by.itacademy.shop.api.dto.user;

import by.itacademy.shop.api.dto.admin.RoleDto;
import lombok.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
        @NotEmpty(message = "Your name can't be empty ")
        @Size(min=3,max=255,message = "Name should be between 2 and 30 characters ")
        private String name;
        @Email(message = "Not valid Email")
        @NotEmpty(message = "Should be not empty")
        private String email;

        @Pattern(regexp = "/+[0-9]{12}",message = "Your number should be with plus and only 12 symbols: +375442...")
        private String phone;

        private String password;

        private List<RoleDto> roles;

        private List<OrderDto>  orders;

}
