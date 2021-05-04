package by.itacademy.shop.api.dto.admin.category;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentCategoryDto {
    private Long id;

    private String title;

    private List<CategoryDto> subcategories;

}
