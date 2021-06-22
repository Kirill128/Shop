package by.senla.daomicroservice.microservices.dto.forall;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestParentCategoryDto {
    private Long id;

    private String title;

    private List<GuestSubCategoryDto> subcategories;

}
