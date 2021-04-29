package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.entities.Order;
import by.itacademy.shop.entities.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    private Order order;
    private Long id;
    private Product product;

    private Integer quantity;

    private Double price;
}
