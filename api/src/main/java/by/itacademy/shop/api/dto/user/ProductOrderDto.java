package by.itacademy.shop.api.dto.user;

import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.api.dto.forall.GuestProductDto;
import by.itacademy.shop.entities.Order;
import by.itacademy.shop.entities.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    private Long id;

    private GuestProductDto product;

    private Integer quantity;

    private Double price;


}
