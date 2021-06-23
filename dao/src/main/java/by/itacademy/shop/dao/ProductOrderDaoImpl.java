package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductOrderDao;
import by.itacademy.shop.entities.ProductOrder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("release")
public class ProductOrderDaoImpl extends GenericDaoImpl<ProductOrder> implements ProductOrderDao {
    public ProductOrderDaoImpl() {
        super(ProductOrder.class);
    }
}
