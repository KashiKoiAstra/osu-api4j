package kashi.koi.auth;

import java.time.Duration;
import java.time.Instant;

// current token
public record Token(
        String token,
        Instant expiresAt) {

    public Token(String token, long expiresInSeconds) {
        this(token, Instant.now().plusSeconds(expiresInSeconds));
    }

    public boolean isExpired() {
        Instant now = Instant.now();
        return now.isAfter(expiresAt) || now.equals(expiresAt);
    }

    public boolean isExpiringSoon(Duration threshold) {
        Instant now = Instant.now();
        return now.plus(threshold).isAfter(expiresAt);
    }

    public boolean isValid() {
        return token != null && !token.isBlank() && !isExpired();
    }
}