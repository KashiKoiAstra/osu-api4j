package kashi.koi.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record User(
        String avatarUrl,
        String contryCode,
        String defaultGroup,
        Integer id,
        Boolean isActive,
        Boolean isBot,
        Boolean isDeleted,
        Boolean isOnline,
        Boolean isSupporter,
        OffsetDateTime lastVisit,
        Boolean pmFriendsOnly,
        String profileColour,
        String username
) {
}
