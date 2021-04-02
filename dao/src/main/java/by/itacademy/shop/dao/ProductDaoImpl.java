package by.itacademy.shop.dao;

import  by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

    protected ProductDaoImpl() {
        super(Product.class);
    }

}
