package kashi.koi.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponse(
        @JsonProperty("expires_in") Long expiresIn,
        @JsonProperty("token_type") String tokenType,
        @JsonProperty("access_token") String accessToken

        // https request format
        // attention: camelCase -> snake_case mapping
){

}
