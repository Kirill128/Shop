package by.itacademy.shop.api.dto;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Product;
import lombok.*;
import java.util.List;
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

    private List<Product> products;
}
