package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.user.OrderDto;
import by.itacademy.shop.entities.Order;
import by.itacademy.shop.entities.ProductOrder;
import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {
    public OrderDto mapOrderToOrderDto(Order source, Lang lang){
        if(source==null)return null;
        return OrderDto.builder()
                .id(source.getId())
                .productOrder(ProductOrderMapper.mapProductOrdersToProductOrderDtos(source.getProductOrder(),lang))
                .price(source.getPrice())
                .status(source.getStatus())
                .submitTime(source.getSubmitTime())
                .build();
    }
    public List<OrderDto> mapOrdersToOrdersDto(List<Order> source,Lang lang){
        if(source==null)return new ArrayList<>();
        return  source.stream().map((e)-> OrderMapper.mapOrderToOrderDto(e,lang)).collect(Collectors.toList());
    }
}
