package kashi.koi;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kashi.koi.api.beatmaps.BeatmapApi;
import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.AuthConfigLoader;
import kashi.koi.auth.OAuthTokenProvider;
import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.DefaultApiHttpClient;

// sdk facade and single entry for all API groups
public class OsuClient {

    private final BeatmapApi beatmapApi;

    private OsuClient(BeatmapApi beatmapScoresApi) {
        this.beatmapApi = beatmapScoresApi;
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

        return new OsuClient(new BeatmapApi(apiHttpClient));
    }

    public BeatmapApi beatmap() {
        return beatmapApi;
    }

}
