package kashi.koi.model.users;


import java.time.OffsetDateTime;

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
