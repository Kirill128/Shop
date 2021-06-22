package by.senla.daomicroservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan(basePackages = {"by.senla.daomicroservice.dao"})
@Slf4j
@EnableJpaRepositories(basePackages = {"by.senla.daomicroservice.dao"})
public class DaoConfiguration {

}
