package by.itacademy.shop.config;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.dao.ProductDaoImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class TestProductDaoImpl {
    @Bean
    public EntityManager entitymanager(){
        return Mockito.mock(EntityManager.class);
    }

    @Bean
    public ProductDao productDao(){
        return new ProductDaoImpl();
    }
}
