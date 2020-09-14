package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        // given
        final UUID customerId = UUID.randomUUID();

        // when
        final CustomerDto customer = customerClient.getCustomerById(customerId);

        // then
        assertThat("Customer cannot be null", customer, is(notNullValue()));
        // and
        assertThat("Customer has name property with value John Doe",
                customer, hasProperty("name", equalTo("John Doe")));
    }

    @Test
    void saveNewCustomer() {
        // given
        final UUID customerId = UUID.randomUUID();
        final CustomerDto newCutomer = CustomerDto.builder()
                .id(customerId)
                .name("John Smith")
                .build();

        // when
        final URI newCustomerLocation = customerClient.saveNewCustomer(newCutomer);

        // then
        assertThat("New cusotmer location cannot be null", newCustomerLocation, is(notNullValue()));
    }

    @Test
    void updateCustomer() {
        // given
        final UUID customerId = UUID.randomUUID();
        final CustomerDto customer = CustomerDto.builder()
                .name("Jim Black")
                .build();

        // when
        customerClient.updateCustomer(customerId, customer);

        // then no exception is thrown
    }

    @Test
    void deleteCustomer() {
        // given
        final UUID customerId = UUID.randomUUID();

        // when
        customerClient.deleteCustomer(customerId);

        // then no exception is thrown
    }
}