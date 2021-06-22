package by.senla.daomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "by.senla.daomicroservice")
@EnableJpaRepositories("by.senla.daomicroservice.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

}
