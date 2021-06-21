package by.senla.microservices.dto.admin;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminParentCategoryDto {
    private Long id;

    private String title;

    private List<AdminCategoryDto> subcategories;

}
