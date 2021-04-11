package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductOrderDao;
import by.itacademy.shop.entities.ProductOrder;
import org.springframework.stereotype.Repository;

@Repository

public class ProductOrderDaoImpl extends GenericDaoImpl<ProductOrder> implements ProductOrderDao {
    protected ProductOrderDaoImpl() {
        super(ProductOrder.class);
    }
}
