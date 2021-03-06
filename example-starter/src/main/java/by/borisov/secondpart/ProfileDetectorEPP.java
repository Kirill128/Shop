package by.borisov.secondpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProfileDetectorEPP implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if(environment.getActiveProfiles().length==0){
            environment.setActiveProfiles("trueProfile");
        }else{
            environment.setActiveProfiles("falseProfile");
        }
    }
}
