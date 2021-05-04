package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProductMapper {
    public GuestProductDto mapProductToGuestProductDto(Product product, Lang lang){
        return GuestProductDto.builder().
                id(product.getId()).
                attributes(product.getAttributes()).
                shortDescription(product.getShortDescription().get(lang.value)).
                price(product.getPrice()).
                category(product.getCategory()).
                photo(product.getPhoto()).
                build();
    }


    public List<GuestProductDto> mapProductsToGuestProductDtos(List<Product> products,Lang lang){
        return products.stream().map(e-> ProductMapper.mapProductToGuestProductDto(e,lang)).collect(Collectors.toList());
    }

    //---------------Product Dto
    public ProductDto mapProductToProductDto(Product product) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return ProductDto.builder().
                id(product.getId()).
                barcode(product.getBarcode()).
                attributes(product.getAttributes()).
                shortDescription(mapper.writeValueAsString(product.getShortDescription())).
                price(product.getPrice()).
                quantityInStorage(product.getQuantityInStorage()).
                category(product.getCategory()).
                photo(product.getPhoto()).
                provider(product.getProvider()).
                build();
    }
    public List<ProductDto> mapProductsToProductDtos(List<Product> products) throws JsonProcessingException {
        List<ProductDto> result=new ArrayList<>(products.size());
        for(Product product: products){
            result.add(ProductMapper.mapProductToProductDto(product));
        }
        return result;
    }


    public Product mapProductDtoToProduct(ProductDto product) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return Product.builder().
                id(product.getId()).
                barcode(product.getBarcode()).
                attributes(product.getAttributes()).
                shortDescription(mapper.readValue(product.getShortDescription(),new TypeReference<HashMap<String, String>>(){})).
                price(product.getPrice()).
                quantityInStorage(product.getQuantityInStorage()).
                category(product.getCategory()).
                photo(product.getPhoto()).
                provider(product.getProvider()).
                build();
    }
    public List<Product> mapProductDtosToProducts(List<ProductDto> products) throws JsonProcessingException {
        List<Product> result = new ArrayList<>(products.size());
        for (ProductDto product : products) {
            result.add(ProductMapper.mapProductDtoToProduct(product));
        }
        return result;
    }
    //---------------
}
