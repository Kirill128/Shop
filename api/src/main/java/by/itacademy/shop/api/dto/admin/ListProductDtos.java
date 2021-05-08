package by.itacademy.shop.api.dto.admin;

import by.itacademy.shop.api.dto.admin.ProductDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListProductDtos {
    private List<ProductDto> productDtoList;

}
