package by.itacademy.shop.configurations;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.dao.ProductDaoImpl;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.itacademy.shop.dao")
public class DaoConfiguration {
   

}
