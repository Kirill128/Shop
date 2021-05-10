package by.itacademy.shop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan("by.itacademy.shop.services")
@Import({DaoConfiguration.class})
public class ServiceConfiguration {

}
