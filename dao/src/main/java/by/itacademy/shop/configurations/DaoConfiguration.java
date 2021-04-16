package by.itacademy.shop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Configuration
@ComponentScan(basePackages = "by.itacademy.shop.dao")
public class DaoConfiguration {

    @Bean
    public EntityManager getEntityManager(){
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("by.itacademy.shop.hibernate.jpa");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
