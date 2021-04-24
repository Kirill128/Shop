package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );
    ProductDto mapProductDto(Product source);
    Product mapProduct(ProductDto source);
    List<ProductDto> mapProductDtos(List<Product> source);
    List<Product> mapProducts(List<ProductDto> source);
}
