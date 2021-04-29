package by.itacademy.shop.api.dto;

import by.itacademy.shop.entities.Category;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestCategoryDto {
    private Long id;

    private String title;

    private Category parentCategory;

}
