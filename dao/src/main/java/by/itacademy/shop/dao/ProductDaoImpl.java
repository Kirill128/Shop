package by.itacademy.shop.dao;
import by.itacademy.shop.api.dao.GenericDao;
import  by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.entities.Product;

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

    protected ProductDaoImpl() {
        super(Product.class);
    }

}
