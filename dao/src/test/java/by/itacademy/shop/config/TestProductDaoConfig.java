package by.itacademy.shop.config;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.dao.ProductDaoImpl;
import by.senla.daomicroservice.microservices.constants.Constants;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.pro.packaged.B;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:/META-INF/test-persistence.properties")
@Slf4j
public class TestProductDaoConfig {
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ProductDao productDao(){
        return new ProductDaoImpl();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.user"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        log.info("JDBC DRIVER =",environment.getRequiredProperty("jdbc.driver"));
        log.info("JDBC URL =",environment.getRequiredProperty("jdbc.url"));
        log.info("JDBC USER =",environment.getRequiredProperty("jdbc.user"));
        log.info("JDBC PASSWORD =",environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource());
        emFactory.setPackagesToScan("by.itacademy.shop.entities");
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties=new Properties();
        properties.setProperty(Constants.HIBERNATE_DIALECT, environment.getRequiredProperty(Constants.HIBERNATE_DIALECT));
        properties.setProperty(Constants.HIBERNATE_SHOW_SQL, environment.getRequiredProperty(Constants.HIBERNATE_SHOW_SQL));
        properties.setProperty(Constants.HIBERNATE_FORMAT_SQL,environment.getRequiredProperty(Constants.HIBERNATE_FORMAT_SQL));
        emFactory.setJpaProperties(properties);

        log.info(Constants.HIBERNATE_DIALECT+" =",environment.getRequiredProperty(Constants.HIBERNATE_DIALECT));
        log.info(Constants.HIBERNATE_SHOW_SQL+" =",environment.getRequiredProperty(Constants.HIBERNATE_SHOW_SQL));
        log.info(Constants.HIBERNATE_FORMAT_SQL+" =",environment.getRequiredProperty(Constants.HIBERNATE_FORMAT_SQL));

        return emFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db.changelog/db.changelog-testmaster.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }
}
