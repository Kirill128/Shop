package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@TestPropertySource("/application-test.properties")
@Transactional
class ProductDaoImplTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void productDaoNotNull(){
        Assertions.assertNotNull(productDao);
    }
    @Test
    public void createSuccess(){
        this.productDao.create(Product.builder().id(2L).build());
    }
    @Test
    public void findAllSuccess(){
        List<Product> products=this.productDao.findAll();
        Assertions.assertTrue(products.isEmpty());

    }
}