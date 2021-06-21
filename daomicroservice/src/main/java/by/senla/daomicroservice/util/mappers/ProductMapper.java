package by.senla.daomicroservice.util.mappers;


import by.senla.daomicroservice.entities.Category;
import by.senla.daomicroservice.entities.Photo;
import by.senla.daomicroservice.entities.Product;
import by.senla.daomicroservice.entities.Provider;
import by.senla.microservices.constants.Lang;
import by.senla.microservices.dto.admin.AdminProductDto;
import by.senla.microservices.dto.forall.GuestProductDto;
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
        if(product==null)return null;
        return GuestProductDto.builder().
                id(product.getId()).
                attributes(product.getAttributes()).
                shortDescription(product.getShortDescription().get(lang.value)).
                price(product.getPrice()).
                category(CategoryMapper.mapCategoryToGuestSubCategoryDto(product.getCategory(),lang)).
                photo(PhotoMapper.mapProductPhotoToGuestProductPhotoDto(product.getPhoto())).
                build();
    }

    public List<GuestProductDto> mapProductsToGuestProductDtos(List<Product> products,Lang lang){
        if(products==null)return new ArrayList<>();
        return products.stream().map(e-> ProductMapper.mapProductToGuestProductDto(e,lang)).collect(Collectors.toList());
    }

    //---------------Product Dto
    public AdminProductDto mapProductToProductDto(Product product) throws JsonProcessingException {
        if(product==null)return null;
        ObjectMapper mapper=new ObjectMapper();
        return AdminProductDto.builder().
                id(product.getId()).
                barcode(product.getBarcode()).
                attributes(product.getAttributes()).
                shortDescription(mapper.writeValueAsString(product.getShortDescription())).
                price(product.getPrice()).
                quantityInStorage(product.getQuantityInStorage()).
                categoryId(product.getCategory().getId()).
                photoId((product.getPhoto()!=null) ? product.getPhoto().getId() : null).
                providerId((product.getProvider()!=null) ? product.getProvider().getId() : null).
                build();
    }
    public List<AdminProductDto> mapProductsToProductDtos(List<Product> products) throws JsonProcessingException {
        if(products==null)return new ArrayList<>();
        List<AdminProductDto> result=new ArrayList<>(products.size());
        for(Product product: products){
            result.add(ProductMapper.mapProductToProductDto(product));
        }
        return result;
    }


    public Product mapProductDtoToProduct(AdminProductDto product) throws JsonProcessingException {
        if(product==null)return null;
        ObjectMapper mapper=new ObjectMapper();
        Category category= Category.builder().id(product.getCategoryId()).build();
        Photo photo = (product.getPhotoId()!=null) ? Photo.builder().id(product.getPhotoId()).build() : null;
        Provider provider= (product.getProviderId()!=null) ? Provider.builder().id(product.getProviderId()).build() : null ;
        return Product.builder().
                id(product.getId()).
                barcode(product.getBarcode()).
                attributes(product.getAttributes()).
                shortDescription(mapper.readValue(product.getShortDescription(),new TypeReference<HashMap<String, String>>(){})).
                price(product.getPrice()).
                quantityInStorage(product.getQuantityInStorage()).
                category(category).
                photo(photo).
                provider(provider).
                build();
    }
    public List<Product> mapProductDtosToProducts(List<AdminProductDto> products) throws JsonProcessingException {
        if(products==null)return new ArrayList<>();
        List<Product> result = new ArrayList<>(products.size());
        for (AdminProductDto product : products) {
            result.add(ProductMapper.mapProductDtoToProduct(product));
        }
        return result;
    }

}
