package by.itacademy.shop.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestCategoryDto {
    private Long id;

    private String title;

    private List<GuestSubCategoryDto> subcategories;

}
