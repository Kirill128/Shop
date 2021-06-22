package by.borisov.secondpart;

import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationContextInitializer implements org.springframework.context.ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        if(configurableApplicationContext.getEnvironment().getActiveProfiles().length==0){
            throw new RuntimeException("без профиля нельзя");
        }
    }
}
