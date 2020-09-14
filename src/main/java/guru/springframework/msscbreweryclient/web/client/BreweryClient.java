package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.config.AppProperties;
import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class BreweryClient {

    public static final String BEER_PATH_V1 = "/api/v1/beers/";

    private final AppProperties props;
    private final RestTemplate restTemplate;

    public BreweryClient(final AppProperties props,
                         final RestTemplateBuilder restTemplateBuilder) {
        this.props = props;
        this.restTemplate = restTemplateBuilder.build();
    }


    public BeerDto getBeerById(final UUID beerId) {
        return restTemplate.getForObject(props.getApiHost() + BEER_PATH_V1 + beerId, BeerDto.class);
    }

    public URI saveNewBeer(final BeerDto beerDto) {
        return restTemplate.postForLocation(props.getApiHost() + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(final UUID beerId, final BeerDto beerDto) {
        restTemplate.put(props.getApiHost() + BEER_PATH_V1 + beerId, beerDto);
    }

    public void deleteBeer(final UUID beerId) {
        restTemplate.delete(props.getApiHost() + BEER_PATH_V1 + beerId);
    }
}
