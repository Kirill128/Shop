package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;
import lombok.experimental.UtilityClass;

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
    public ProductDto mapProductToProductDto(Product product){
        return ProductDto.builder().
                id(product.getId()).
                barcode(product.getBarcode()).
                attributes(product.getAttributes()).
                shortDescription(product.getShortDescription()).
                price(product.getPrice()).
                quantityInStorage(product.getQuantityInStorage()).
                category(product.getCategory()).
                photo(product.getPhoto()).
                provider(product.getProvider()).
                build();
    }
    public List<ProductDto> mapProductsToProductDtos(List<Product> products){
        return products.stream().map(e-> ProductMapper.mapProductToProductDto(e)).collect(Collectors.toList());
    }


    public Product mapProductDtoToProduct(ProductDto product){
        return Product.builder().
                id(product.getId()).
                barcode(product.getBarcode()).
                attributes(product.getAttributes()).
                shortDescription(product.getShortDescription()).
                price(product.getPrice()).
                quantityInStorage(product.getQuantityInStorage()).
                category(product.getCategory()).
                photo(product.getPhoto()).
                provider(product.getProvider()).
                build();
    }
    public List<Product> mapProductDtosToProducts(List<ProductDto> products){
        return products.stream().map(e-> ProductMapper.mapProductDtoToProduct(e)).collect(Collectors.toList());
    }
    //---------------
}
