package by.itacademy.shop.configuration;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = {"by.itacademy.shop.dao","by.itacademy.shop.api"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource(value = "classpath:/META-INF/persistence.properties")
@Slf4j
public class DaoConfiguration {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
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
        properties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        emFactory.setJpaProperties(properties);

        log.info("hibernate.dialect =",environment.getRequiredProperty("hibernate.dialect"));
        log.info("hibernate.show_sql =",environment.getRequiredProperty("hibernate.show_sql"));
        log.info("hibernate.format_sql =",environment.getRequiredProperty("hibernate.format_sql"));

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
        liquibase.setChangeLog("classpath:/db.changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }
}
