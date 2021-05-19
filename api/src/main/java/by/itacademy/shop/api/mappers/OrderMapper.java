package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.AdminOrderDto;
import by.itacademy.shop.api.dto.user.OrderDto;
import by.itacademy.shop.entities.Order;
import by.itacademy.shop.utilenum.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.experimental.UtilityClass;

import javax.sound.midi.Soundbank;
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
    public AdminOrderDto mapOrderToAdminOrderDto(Order source) throws JsonProcessingException {
        if(source==null)return null;
        return AdminOrderDto.builder()
                .id(source.getId())
                .productOrder(ProductOrderMapper.mapProductOrderSToAdminProductOrderDtos(source.getProductOrder()))
                .price(source.getPrice())
                .status(source.getStatus())
                .submitTime(source.getSubmitTime())
                .build();
    }
    public List<AdminOrderDto> mapOrdersToAdminOrderDtos(List<Order> source) throws JsonProcessingException {
        ArrayList<AdminOrderDto> res=new ArrayList<>();
        if(source==null)return res;
        for(Order order : source){
            res.add(OrderMapper.mapOrderToAdminOrderDto(order));
        }
        return res;
    }
}
