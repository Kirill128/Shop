package test.by.itacademy.shop.config;

import by.itacademy.shop.api.dao.ProductDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDaoConfigForTest {
    @Bean
    public ProductDao productDao() {
        return Mockito.mock(ProductDao.class);
    }

}
