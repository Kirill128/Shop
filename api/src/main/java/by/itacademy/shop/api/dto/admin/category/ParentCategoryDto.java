package by.itacademy.shop.api.dto.admin.category;

import by.itacademy.shop.entities.Category;
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

    private List<Category> subcategories;

}
