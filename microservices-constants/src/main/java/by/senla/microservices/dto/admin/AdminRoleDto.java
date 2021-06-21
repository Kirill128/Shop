package by.senla.microservices.dto.admin;

import lombok.*;

import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleDto {

    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminRoleDto that = (AdminRoleDto) o;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
