package by.itacademy.shop.api.dto.admin.category;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoFromFront {
    private Long id;

    private String title;

    private long parentCategoryId;

}
