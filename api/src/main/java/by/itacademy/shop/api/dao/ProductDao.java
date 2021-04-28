package by.itacademy.shop.api.dao;

import by.itacademy.shop.api.dto.ProductDto;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.locale.Lang;

import java.util.List;

public interface ProductDao extends GenericDao<Product> {
    List<Product> getLimitedProductsWithOffset(int pageNum,int pageSize);
}
