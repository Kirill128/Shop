package by.itacademy.shop.dao;

import  by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.converter.JsonMapConverter;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.User;
import liquibase.pro.packaged.J;

import javax.persistence.Query;


public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao{

    protected ProductDaoImpl() {
        super(Product.class);
    }

    public void createNative(Product product){
        JsonMapConverter jsonMapConverter=new JsonMapConverter();
        Query query=entityManager.createQuery("insert into Product (id,attributes,shortDescription,category,provider,photo,quantityInStorage) select " +
                "'"+product.getId()+"',"+
                "'"+jsonMapConverter.convertToDatabaseColumn(product.getAttributes())+"',"+
                "'"+jsonMapConverter.convertToDatabaseColumn(product.getShortDescription())+"',"+
                "'"+product.getCategory().getId()+"',"+
                "'"+product.getProvider().getId()+"',"+
                "'"+product.getPhoto().getId()+"',"+
                "'"+product.getQuantityInStorage()+"' from Product");
    }
}
