package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.user.ProductOrderDto;
import by.itacademy.shop.utilenum.Lang;

import java.util.List;

public interface ProductOrderService {
    ProductOrderDto createProductOrder(ProductOrderDto user);
    ProductOrderDto find(long id);
    ProductOrderDto find(long id, Lang lang);
    void update(ProductOrderDto user);
    void delete(long id);

    List<ProductOrderDto> getAllProductOrders();
}
