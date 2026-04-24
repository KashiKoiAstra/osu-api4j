package kashi.koi.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserExtended(
        String coverUrl,
        String discord,
        Boolean hasSupported,
        String interests,
        OffsetDateTime joinDate,
        String location,
        Integer maxBlocks,
        Integer maxFriends,
        String occupation,
        String playmode,
        String[] playstyle,
        Integer postCount,
        Integer profileHue,
        String[] profileOrder,
        String title,
        String titleUrl,
        String twitter,
        String username,
        String website
) {
}
