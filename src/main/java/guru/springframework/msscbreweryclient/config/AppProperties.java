package guru.springframework.msscbreweryclient.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
public class AppProperties {
    String apiHost;
    HttpClient httpClient;

    @Value
    public static class HttpClient {
        Integer maxTotal;
        Integer defaultMaxPerRoute;
        Integer connectionRequestTimeout;
        Integer connectTimeout;
    }
}
