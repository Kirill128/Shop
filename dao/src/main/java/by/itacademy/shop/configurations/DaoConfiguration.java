package by.itacademy.shop.configurations;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.dao.ProductDaoImpl;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "by.itacademy.shop.dao")
public class DaoConfiguration {
//    @Bean
//    public SpringLiquibase liquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
//        liquibase.setDataSource();
//        return liquibase;
//    }
}
