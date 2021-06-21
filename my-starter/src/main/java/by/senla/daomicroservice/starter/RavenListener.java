package by.senla.daomicroservice.starter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class RavenListener implements ApplicationListener<ContextRefreshedEvent> {
    private final RavenProperties ravenProperties;

    public RavenListener(RavenProperties ravenProperties) {
        this.ravenProperties = ravenProperties;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.printf("отправляем ворона в ");
        ravenProperties.getWhere().forEach(System.out::printf);
        System.out.println();
    }
}
