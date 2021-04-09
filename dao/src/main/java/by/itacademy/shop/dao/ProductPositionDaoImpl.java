package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dao.ProductPositionDao;
import by.itacademy.shop.entities.Product;

public class ProductPositionDaoImpl extends GenericDaoImpl<Product> implements ProductPositionDao {
    protected ProductPositionDaoImpl() {
        super(Product.class);
    }
}
