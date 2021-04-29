package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto user);
    OrderDto find(long id);
    void update(OrderDto user);
    void delete(long id);

    List<OrderDto> getAllOrders();

}
