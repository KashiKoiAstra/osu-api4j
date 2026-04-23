package kashi.koi.auth;

import java.time.Duration;
import java.time.Instant;

public record Token(
        String token,
        Instant expiresAt
) {
    public Token(String token, long expiresIn) {
        this(token, Instant.now().plusSeconds(expiresIn));
    }

    public boolean isExpired() {
        Instant now = Instant.now();
        return now.isAfter(expiresAt) || now.equals(expiresAt);
    }

    public boolean isExpiringSoon(Duration thresholdSeconds) {
        Instant now = Instant.now();
        return now.plus(thresholdSeconds).isAfter(expiresAt);
    }

    public boolean isValid() {
        return token != null && !token.isBlank() && !isExpired();
    }
}
