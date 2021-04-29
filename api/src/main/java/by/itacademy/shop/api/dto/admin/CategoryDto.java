package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.entities.Category;
import lombok.*;

import java.util.Map;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private Map<String,String> title;

    private Category parentCategory;

}
