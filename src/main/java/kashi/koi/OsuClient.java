package kashi.koi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kashi.koi.api.beatmaps.BeatmapsApi;
import kashi.koi.api.users.UsersApi;
import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.AuthConfigLoader;
import kashi.koi.auth.OAuthTokenProvider;
import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.DefaultApiHttpClient;

import java.net.http.HttpClient;

// sdk facade and single entry for all API groups
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
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenProvider tokenProvider = new OAuthTokenProvider(config, httpClient, objectMapper);
        ApiHttpClient apiHttpClient = new DefaultApiHttpClient(config, tokenProvider, httpClient, objectMapper);

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return new OsuClient(
                new BeatmapsApi(apiHttpClient),
                new UsersApi(apiHttpClient)
        );
    }

    public BeatmapsApi beatmaps() {
        return beatmapsApi;
    }

    public UsersApi users() {
        return usersApi;
    }

}
