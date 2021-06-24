package test.by.itacademy.shop.config;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.services.ProductService;
import by.itacademy.shop.services.ProductServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestProductServiceConfig {
    @Bean
    public ProductDao productDao(){
        return  Mockito.mock(ProductDao.class);
    }
    @Bean
    public ProductService productService(){
        return new ProductServiceImpl(productDao());
    }
}
