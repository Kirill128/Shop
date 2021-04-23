package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.OrderDto;
import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto user);
    OrderDto find(long id);
    void update(OrderDto user);
    void delete(long id);

    List<OrderDto> getAllOrders();

}
