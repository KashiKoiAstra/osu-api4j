package kashi.koi.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

// http response format
public record TokenResponse(
        @JsonProperty("expires_in") Long expiresIn,
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("access_token") String accessToken

// attention: camelCase -> snake_case mapping
) {
}