package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.entities.Product;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoImplTest {

    @Test
    public void create(){
        ProductDao productDao=new ProductDaoImpl();
        Product first=Product.builder()
                .id(4690612002200L)
                .quantityInStorage(5)
                .name("SSSSSS name")
                .price(12.0)
                .build();
        Product product = productDao.create(first);
        System.out.println(product);
    }
    @Test
    public void find(){
        ProductDao productDao=new ProductDaoImpl();
        Product product=productDao.find(12134L);
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
        productDao.update(Product.builder()
                .id(4690612003917L)
                .quantityInStorage(5)
                .name("sECOND name")
                .price(12.0)
                .build());
    }
    @Test
    public void findAll(){

    }

}