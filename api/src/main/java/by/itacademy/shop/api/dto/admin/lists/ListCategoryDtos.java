package by.itacademy.shop.api.dto.admin.lists;

import by.itacademy.shop.api.dto.admin.category.CategoryDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListCategoryDtos {
    private List<CategoryDto> categoryDtoList;

}
