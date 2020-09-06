package guru.springframework.msscbreweryclient.web.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BeerDto {
    UUID id;
    String name;
    String style;
    Long upc;
}
