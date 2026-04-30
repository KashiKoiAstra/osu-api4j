package kashi.koi.auth;

public record AuthConfig(
        String clientId,
        String clientSecret,
        String tokenUrl,
        String apiBaseUrl,
        String scope) {

    private static final String ENV_CLIENT_ID = "OSU_CLIENT_ID";
    private static final String ENV_CLIENT_SECRET = "OSU_CLIENT_SECRET";
    private static final String ENV_TOKEN_URL = "OSU_TOKEN_URL";
    private static final String ENV_API_BASE_URL = "OSU_API_BASE_URL";
    private static final String ENV_SCOPE = "OSU_SCOPE";

    public static final String DEFAULT_TOKEN_URL = "https://osu.ppy.sh/oauth/token";
    public static final String DEFAULT_API_BASE_URL = "https://osu.ppy.sh/api/v2";
    public static final String DEFAULT_SCOPE = "public";

    public AuthConfig {
        clientId = requireValue(clientId, "clientId");
        clientSecret = requireValue(clientSecret, "clientSecret");
        tokenUrl = requireValue(tokenUrl, "tokenUrl");
        apiBaseUrl = requireValue(apiBaseUrl, "apiBaseUrl");
        scope = requireValue(scope, "scope");
    }

    public static AuthConfig fromEnv() {
        String clientId = System.getenv(ENV_CLIENT_ID);
        String clientSecret = System.getenv(ENV_CLIENT_SECRET);
        if (clientId == null || clientId.isBlank() || clientSecret == null || clientSecret.isBlank()) {
            throw new IllegalStateException(
                    "Missing required environment variables: " + ENV_CLIENT_ID + " and " + ENV_CLIENT_SECRET);
        }
        return new AuthConfig(
                clientId,
                clientSecret,
                System.getenv().getOrDefault(ENV_TOKEN_URL, DEFAULT_TOKEN_URL),
                System.getenv().getOrDefault(ENV_API_BASE_URL, DEFAULT_API_BASE_URL),
                System.getenv().getOrDefault(ENV_SCOPE, DEFAULT_SCOPE));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String tokenUrl = DEFAULT_TOKEN_URL;
        private String apiBaseUrl = DEFAULT_API_BASE_URL;
        private String scope = DEFAULT_SCOPE;

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder tokenUrl(String tokenUrl) {
            this.tokenUrl = tokenUrl;
            return this;
        }

        public Builder apiBaseUrl(String apiBaseUrl) {
            this.apiBaseUrl = apiBaseUrl;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public AuthConfig build() {
            return new AuthConfig(clientId, clientSecret, tokenUrl, apiBaseUrl, scope);
        }
    }

    private static String requireValue(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required value: " + name);
        }
        return value.trim();
    }
}
