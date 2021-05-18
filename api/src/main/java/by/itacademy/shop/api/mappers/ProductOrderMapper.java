package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.user.ProductOrderDto;
import by.itacademy.shop.entities.ProductOrder;
import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProductOrderMapper {
    public ProductOrderDto mapProductOrderToProductOrderDto(ProductOrder source, Lang lang){
        if(source==null)return null;
        return ProductOrderDto.builder().
                id(source.getId()).
                product(ProductMapper.mapProductToGuestProductDto(source.getProduct(),lang)).
                quantity(source.getQuantity()).
                price(source.getPrice()).
                build();
    }
    public List<ProductOrderDto> mapProductOrdersToProductOrderDtos(List<ProductOrder> source,Lang lang){
        if(source==null)return new ArrayList<>();
        return source.stream().map((e)->ProductOrderMapper.mapProductOrderToProductOrderDto(e,lang)).collect(Collectors.toList());
    }


}
