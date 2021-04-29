package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.GuestProductDto;
import by.itacademy.shop.api.dto.admin.ProductDto;
import by.itacademy.shop.locale.Lang;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ProductService {

    //Users
    List<GuestProductDto> getAllProducts(Lang lang);
    List<GuestProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize,Lang lang);
    GuestProductDto find(long id, Lang lang);


    //Admin
    ProductDto createProduct(ProductDto user);
    ProductDto findFullInfo(long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize);
    void update(ProductDto user);
    void delete(long id);
    List<ProductDto> parseXLSOrXlSXFile(MultipartFile file, InputStream inputStream) throws IOException;


}
