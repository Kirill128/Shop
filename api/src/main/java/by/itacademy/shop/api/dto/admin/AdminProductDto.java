package by.itacademy.shop.api.dto.admin;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminProductDto {

    private Long id;

    private String barcode;

    private Map<String,String> attributes;

    private String shortDescription;

    private Double price;

    private Integer quantityInStorage;

    private long categoryId;

    private Long photoId;

    private Long providerId;


    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", attributes=" + attributes +
                ", shortDescription=" + shortDescription +
                ", price=" + price +
                ", quantityInStorage=" + quantityInStorage +
                ", category=" + categoryId +
                ", photo=" + photoId +
                ", provider=" + providerId +
                '}';
    }


}


