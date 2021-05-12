package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.user.ProductOrderDto;
import by.itacademy.shop.entities.ProductOrder;
import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductOrderMapper {
    public ProductOrderDto mapProductOrderToProductOrderDto(ProductOrder source, Lang lang){
        return ProductOrderDto.builder().
                id(source.getId()).
                product(ProductMapper.mapProductToGuestProductDto(source.getProduct(),lang)).
                quantity(source.getQuantity()).
                price(source.getPrice()).
                build();
    }
}
