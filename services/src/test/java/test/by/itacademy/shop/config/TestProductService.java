package test.by.itacademy.shop.config;

import by.itacademy.shop.api.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestProductService {
    @Bean
    public ProductService productService(){
        return Mock
    }
}
