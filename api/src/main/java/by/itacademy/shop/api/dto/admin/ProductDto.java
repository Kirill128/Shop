package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Provider;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String barcode;

    private Map<String,String> attributes;

    private Map<String,String> shortDescription;

    private Double price;

    private Integer quantityInStorage;

    private Category category;

    private Photo photo;

    private Provider provider;


    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", attributes=" + attributes +
                ", shortDescription=" + shortDescription +
                ", price=" + price +
                ", quantityInStorage=" + quantityInStorage +
                ", category=" + category +
                ", photo=" + photo +
                ", provider=" + provider +
                '}';
    }


}


