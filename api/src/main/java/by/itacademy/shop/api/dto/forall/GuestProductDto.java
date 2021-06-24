package by.itacademy.shop.api.dto.forall;

import lombok.*;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestProductDto that = (GuestProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(attributes, that.attributes) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(price, that.price) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attributes, shortDescription, price, category, photo);
    }

}
