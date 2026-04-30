package kashi.koi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kashi.koi.api.beatmaps.BeatmapsApi;
import kashi.koi.api.users.UsersApi;
import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.AuthConfigLoader;
import kashi.koi.auth.OAuthTokenProvider;
import kashi.koi.auth.TokenStore;
import kashi.koi.auth.TokenStorage;
import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.DefaultApiHttpClient;

import java.net.http.HttpClient;

public class OsuClient {

    private final BeatmapsApi beatmapsApi;
    private final UsersApi usersApi;

    private OsuClient(BeatmapsApi beatmapsApi, UsersApi usersApi) {
        this.beatmapsApi = beatmapsApi;
        this.usersApi = usersApi;
    }

    public static OsuClient createDefault() {
        return createDefault(AuthConfigLoader.load());
    }

    public static OsuClient createDefault(AuthConfig config) {
        return builder().authConfig(config).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public BeatmapsApi beatmaps() { return beatmapsApi; }
    public UsersApi users() { return usersApi; }

    public static class Builder {
        private AuthConfig authConfig;
        private String clientId;
        private String clientSecret;
        private String tokenUrl = AuthConfig.DEFAULT_TOKEN_URL;
        private String apiBaseUrl = AuthConfig.DEFAULT_API_BASE_URL;
        private String scope = AuthConfig.DEFAULT_SCOPE;
        private TokenStore tokenStore;
        private HttpClient httpClient;
        private ObjectMapper objectMapper;

        public Builder authConfig(AuthConfig authConfig) {
            this.authConfig = authConfig;
            return this;
        }

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

        public Builder tokenStore(TokenStore tokenStore) {
            this.tokenStore = tokenStore;
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public OsuClient build() {
            AuthConfig config = resolveAuthConfig();
            ObjectMapper mapper = resolveObjectMapper();
            HttpClient client = resolveHttpClient();
            TokenStore store = resolveTokenStore(mapper);
            OAuthTokenProvider tokenProvider = new OAuthTokenProvider(config, client, mapper, store);
            ApiHttpClient apiHttpClient = new DefaultApiHttpClient(config, tokenProvider, client, mapper);

            return new OsuClient(
                    new BeatmapsApi(apiHttpClient),
                    new UsersApi(apiHttpClient));
        }

        private AuthConfig resolveAuthConfig() {
            if (authConfig != null) {
                return authConfig;
            }
            if (clientId != null && clientSecret != null) {
                return new AuthConfig(clientId, clientSecret, tokenUrl, apiBaseUrl, scope);
            }
            return AuthConfigLoader.load();
        }

        private ObjectMapper resolveObjectMapper() {
            if (objectMapper != null) {
                return objectMapper;
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper;
        }

        private HttpClient resolveHttpClient() {
            return httpClient != null ? httpClient : HttpClient.newHttpClient();
        }

        private TokenStore resolveTokenStore(ObjectMapper mapper) {
            return tokenStore != null ? tokenStore : new TokenStorage(mapper);
        }
    }
}
