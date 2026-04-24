package kashi.koi.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record KudosuHistory(
        Integer id,
        String action,
        Integer amount,
        String model,
        OffsetDateTime createdAt,
        Giver giver,
        Post post
) {
}
