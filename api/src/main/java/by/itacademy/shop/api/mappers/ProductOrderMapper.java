package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.AdminProductOrderDto;
import by.itacademy.shop.api.dto.user.ProductOrderDto;
import by.itacademy.shop.entities.ProductOrder;
import by.senla.daomicroservice.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public List<ProductOrderDto> mapProductOrdersToProductOrderDtos(List<ProductOrder> source, Lang lang){
        if(source==null)return new ArrayList<>();
        return source.stream().map(e->ProductOrderMapper.mapProductOrderToProductOrderDto(e,lang)).collect(Collectors.toList());
    }

    public AdminProductOrderDto mapProductOrderToAdminProductOrderDto(ProductOrder source) throws JsonProcessingException {
        if(source==null)return null;
        return AdminProductOrderDto.builder().
                id(source.getId()).
                product(ProductMapper.mapProductToProductDto(source.getProduct())).
                quantity(source.getQuantity()).
                price(source.getPrice()).
                build();
    }
    public List<AdminProductOrderDto> mapProductOrderSToAdminProductOrderDtos(List<ProductOrder> source) throws JsonProcessingException{
        ArrayList<AdminProductOrderDto> res=new ArrayList<>();
        if(source==null)return res;
        for(ProductOrder productOrder : source){
            res.add(ProductOrderMapper.mapProductOrderToAdminProductOrderDto(productOrder));
        }
        return res;
    }

}
