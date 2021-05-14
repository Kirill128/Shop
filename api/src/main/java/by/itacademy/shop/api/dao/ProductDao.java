package by.itacademy.shop.api.dao;

import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.utilenum.Lang;

public interface ProductDao extends GenericDao<Product> {
    SimplePage<Product> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria, Lang lang);
}
