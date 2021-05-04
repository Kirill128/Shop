package by.itacademy.shop.api.dto.admin.category;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String title;

    private ParentCategoryDto parentCategoryDto;

}
