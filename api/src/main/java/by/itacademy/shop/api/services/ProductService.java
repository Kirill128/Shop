package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto user);
    ProductDto find(long id);
    void update(ProductDto user);
    void delete(long id);

    List<ProductDto> getAllProducts();
    List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize);
    List<ProductDto> parseXLSOrXlSXFile(MultipartFile file, InputStream inputStream) throws IOException;

}
