package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.config.AppProperties;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class CustomerClient {

    public static final String CUSTOMER_PATH_V1 = "/api/v1/customers/";

    private final AppProperties props;
    private final RestTemplate restTemplate;

    public CustomerClient(final AppProperties props,
                          final RestTemplateBuilder restTemplateBuilder) {
        this.props = props;
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(final UUID customerId) {
        return restTemplate.getForObject(props.getApiHost() + CUSTOMER_PATH_V1 + customerId, CustomerDto.class);
    }

    public URI saveNewCustomer(final CustomerDto customerDto) {
        return restTemplate.postForLocation(props.getApiHost() + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(final UUID customerId, final CustomerDto customerDto) {
        restTemplate.put(props.getApiHost() + CUSTOMER_PATH_V1 + customerId, customerDto);
    }

    public void deleteCustomer(final UUID customerId) {
        restTemplate.delete(props.getApiHost() + CUSTOMER_PATH_V1 + customerId);
    }
}
