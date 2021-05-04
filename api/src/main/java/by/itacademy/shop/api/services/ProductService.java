package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.locale.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    //Users
    List<GuestProductDto> getAllProducts(Lang lang);
    List<GuestProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize,Lang lang);
    GuestProductDto find(long id, Lang lang);


    //Admin
    ProductDto createProduct(ProductDto user) throws JsonProcessingException;
    ProductDto findFullInfo(long id) throws JsonProcessingException;
    List<ProductDto> getAllProducts() throws JsonProcessingException;
    List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize) throws JsonProcessingException;
    void update(ProductDto user) throws JsonProcessingException;
    void delete(long id);
    List<ProductDto> parseXLSOrXlSXFile(MultipartFile file,Lang lang) throws IOException;
    List<ProductDto> createProducts(List<ProductDto> productDtos) throws JsonProcessingException;

}
