package by.itacademy.shop.api.dto.admin.category;

import by.itacademy.shop.entities.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String title;

    private Category parentCategory;

    private List<SubCategoryDto> subCategories;
}
