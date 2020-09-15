package guru.springframework.msscbreweryclient.config;


import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ApacheHCRestTemplateCustomizer implements RestTemplateCustomizer {

    private final AppProperties props;

    /**
     * Creates blocking Apache HTTP Client for restTemplate.
     */
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        // Configure total max or per route limits for persistent connections
        // that can be kept in the pool or leased by the connection manager.
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(props.getHttpClient().getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(props.getHttpClient().getDefaultMaxPerRoute());

        // Request configuration can be overridden at the request level.
        // They will take precedence over the one set at the client level.
        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(props.getHttpClient().getConnectionRequestTimeout())
                .setConnectTimeout(props.getHttpClient().getConnectTimeout())
                .build();

        final CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
