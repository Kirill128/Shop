package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.CategoryDao;
import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Product;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoImplTest {

    @Test
    @Transactional
    public void create(){
        ProductDao productDao=new ProductDaoImpl();
        CategoryDao categoryDao=new CategoryDaoImpl();
        PhotoDao photoDao =new PhotoDaoImpl();
        ProviderDao providerDao=new ProviderDaoImpl();

        Map<String,String> map=new HashMap<>();
        map.put("RU","TEST PRODUCT CREATE");
        Product product= Product.builder()
                .id(100L)
                .attributes(new HashMap<>())
                .category(categoryDao.find(2L))
                .photo(photoDao.find(1L))
                .price(2.5)
                .provider(providerDao.find(1L))
                .quantityInStorage(2)
                .shortDescription(map)
                .build();
        productDao.create(product);
    }
    @Test
    public void find(){
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.find(1L);
        System.out.println(product);
    }
    @Test
    public void delete(){
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.find(4690612019017L);
        if(product!=null)productDao.delete(product);
    }
    @Test
    public void update(){
        ProductDao productDao=new ProductDaoImpl();

    }
    @Test
    public void findAll(){

    }

}