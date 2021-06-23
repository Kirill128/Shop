package by.senla.daomicroservice.microservices.dto.admin;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdminListProductDtos {
    private List<AdminProductDto> productDtoList;

}
