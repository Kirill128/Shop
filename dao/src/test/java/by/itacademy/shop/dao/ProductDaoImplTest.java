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

    }
    @Test
    public void liquibase(){

    }
    @Test
    public void findAll(){

    }

}