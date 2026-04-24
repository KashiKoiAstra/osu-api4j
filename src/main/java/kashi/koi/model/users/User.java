package kashi.koi.model.users;


import java.time.OffsetDateTime;

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
