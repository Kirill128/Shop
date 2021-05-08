package by.itacademy.shop.api.dto.admin;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String title;

    private Long parentCategoryId;

    private ParentCategoryDto parentCategoryDto;

}
