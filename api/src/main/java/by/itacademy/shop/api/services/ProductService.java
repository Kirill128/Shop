package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.entities.Product;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto user);
    ProductDto find(long id);
    void update(ProductDto user);
    void delete(long id);

    List<ProductDto> getAllProducts();
    List<ProductDto> getLimitedProductsWithOffset(int pageNum, int pageSize);


}
