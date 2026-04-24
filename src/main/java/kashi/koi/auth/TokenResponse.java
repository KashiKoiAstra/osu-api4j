package kashi.koi.auth;


public record TokenResponse(
        Long expiresIn,
        String tokenType,
        String accessToken
) {
}