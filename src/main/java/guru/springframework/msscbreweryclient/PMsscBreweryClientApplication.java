package guru.springframework.msscbreweryclient;

import guru.springframework.msscbreweryclient.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class PMsscBreweryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PMsscBreweryClientApplication.class, args);
    }

}
