package by.borisov.conditionalexamples;

import org.springframework.context.event.ContextRefreshedEvent;

//@Component
//@ConditionalOnRaven
public class SecondRavenListener extends RavenListener{
    public SecondRavenListener(RavenProperties ravenProperties) {
        super(ravenProperties);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Event="+contextRefreshedEvent);
    }
}
