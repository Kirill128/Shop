package by.senla.daomicroservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"by.senla.daomicroservice.controller"})
@EnableAspectJAutoProxy
@Import({ServiceConfiguration.class})//ShopSecurityConfigurerAdapter.class
@EnableWebMvc
public class RestConfiguration implements WebMvcConfigurer {

}
