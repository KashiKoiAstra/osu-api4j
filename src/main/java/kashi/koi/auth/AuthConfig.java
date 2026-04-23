package kashi.koi.auth;

// https request format
public record AuthConfig(
        String clientId,
        String clientSecret,
        String tokenUrl,
        String apiBaseUrl,
        String scope) {
    public AuthConfig {
        clientId = requireValue(clientId, "clientId");
        clientSecret = requireValue(clientSecret, "clientSecret");
        tokenUrl = requireValue(tokenUrl, "tokenUrl");
        apiBaseUrl = requireValue(apiBaseUrl, "apiBaseUrl");
        scope = requireValue(scope, "scope");
    }

    private static String requireValue(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required value: " + name);
        }
        return value.trim();
    }
}
