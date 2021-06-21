package by.senla.microservices.dto.admin;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminProductOrderDto {
    private Long id;

    private AdminProductDto product;

    private Integer quantity;

    private Double price;
}
