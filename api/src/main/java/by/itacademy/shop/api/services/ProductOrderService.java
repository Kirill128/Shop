package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.ProductOrderDto;

import java.util.List;

public interface ProductOrderService {
    ProductOrderDto createUser(ProductOrderDto user);
    ProductOrderDto find(long id);
    void update(ProductOrderDto user);
    void delete(long id);

    List<ProductOrderDto> getAllProductOrders();
}
