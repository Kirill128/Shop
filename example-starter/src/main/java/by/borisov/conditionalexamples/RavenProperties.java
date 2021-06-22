package by.borisov.conditionalexamples;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties("raven")
public class RavenProperties {
    List<String> where;
    String include;
}
