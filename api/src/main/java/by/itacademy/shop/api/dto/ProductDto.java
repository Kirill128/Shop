package by.itacademy.shop.api.dto;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.ProductOrder;
import by.itacademy.shop.entities.Provider;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Map<String,String> attributes;

    private Map<String,String> shortDescription;

    private Double price;

    private Integer quantityInStorage;

    private Category category;

    private Photo photo;

    private Provider provider;

    private List<ProductOrder> productOrders;
}
