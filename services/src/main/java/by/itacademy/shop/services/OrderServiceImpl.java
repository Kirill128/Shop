package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.OrderDao;
import by.itacademy.shop.api.dto.user.OrderDto;
import by.itacademy.shop.api.services.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderDto createOrder(OrderDto user) {
        return null;
    }

    @Override
    public OrderDto find(long id) {
        return null;
    }

    @Override
    public void update(OrderDto user) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<OrderDto> getAllOrders() {
        return null;
    }
}
