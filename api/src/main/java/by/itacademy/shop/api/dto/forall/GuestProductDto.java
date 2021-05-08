package by.itacademy.shop.api.dto.forall;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
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

    private Category category;

    private Photo photo;

}
