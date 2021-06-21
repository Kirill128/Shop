package by.senla.microservices.dto.admin;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCategoryDto {
    private Long id;

    private String title;

    private Long parentCategoryId;

    private AdminParentCategoryDto parentCategoryDto;

}
