package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.OrderDao;
import by.itacademy.shop.entities.Order;
import org.springframework.stereotype.Repository;

@Repository

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    protected OrderDaoImpl() {
        super(Order.class);
    }
}
