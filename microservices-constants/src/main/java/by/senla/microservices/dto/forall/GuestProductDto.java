package by.senla.microservices.dto.forall;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestProductDto {
    private Long id;

    private Map<String,String> attributes;

    private String shortDescription;

    private Double price;

    private GuestSubCategoryDto category;

    private GuestProductPhotoDto photo;

}
