package by.itacademy.shop.dao;

import  by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getLimitedProductsWithOffset(int pageNumber,int pageSize) {

        Query query=entityManager.createQuery("From Product");
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        List<Product> products=(List<Product>) query.getResultList();

        return products;
    }

}
