package by.itacademy.shop.api.dto.user;

import by.itacademy.shop.api.dto.forall.GuestProductDto;
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
