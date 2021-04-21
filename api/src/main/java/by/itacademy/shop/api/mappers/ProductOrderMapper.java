package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.ProductOrderDto;
import by.itacademy.shop.entities.ProductOrder;
import org.mapstruct.factory.Mappers;

public interface ProductOrderMapper {
    ProductOrderMapper INSTANCE = Mappers.getMapper( ProductOrderMapper.class );
    ProductOrderDto mapUserDto(ProductOrder source);
    ProductOrder mapUser(ProductOrderDto source);
}
