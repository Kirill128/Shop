package by.itacademy.shop.api.dao;

import by.itacademy.shop.entities.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Product> getLimitedProductsWithOffset(int pageNum,int pageSize);
}
