package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.OrderDao;
import by.itacademy.shop.entities.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("release")
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }
}
