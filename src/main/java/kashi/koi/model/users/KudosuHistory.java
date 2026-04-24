package kashi.koi.model.users;


import java.time.OffsetDateTime;

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
