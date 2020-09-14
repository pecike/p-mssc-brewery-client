package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;


@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById() {
        // given
        final UUID beerId = UUID.randomUUID();

        // when
        final BeerDto beerDto = breweryClient.getBeerById(beerId);

        // then
        assertThat("BeerDto cannot be null", beerDto, is(notNullValue()));
        // and
        assertThat("BeerDto has name property with value Galaxy Cat",
                beerDto, hasProperty("name", equalTo("Galaxy Cat")));
    }

    @Test
    void saveNewBeer() {
        // given
        final UUID beerId = UUID.randomUUID();
        final BeerDto beerDto = BeerDto.builder()
                .id(beerId)
                .name("Krušovice")
                .style("Lager")
                .build();

        // when
        final URI newBeerLocation = breweryClient.saveNewBeer(beerDto);

        // then
        assertThat("New beer location cannot be null", newBeerLocation, is(notNullValue()));
        // and
        assertThat("New beer ID has to be part of the location",
                newBeerLocation.toString(), endsWith(BreweryClient.BEER_PATH_V1 + beerId));
    }

    @Test
    void updateBeer() {
        // given
        final UUID beerId = UUID.randomUUID();
        final BeerDto beerDto = BeerDto.builder()
                .name("Zlatý Bažant")
                .style("Lager")
                .build();

        // when
        breweryClient.updateBeer(beerId, beerDto);

        // then no exception is thrown
    }

    @Test
    void deleteBeer() {
        // given
        final UUID beerId = UUID.randomUUID();

        // when
        breweryClient.deleteBeer(beerId);

        // then no exception is thrown
    }
}