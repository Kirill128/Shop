package by.itacademy.shop.api.dto.admin;

import lombok.*;

import java.util.List;
import java.util.Set;

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
        private Set<AdminRoleDto> roles;
        private List<AdminOrderDto> orders;
        private Long roleForActionId;
}
