package kashi.koi;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import kashi.koi.api.beatmaps.BeatmapScoresApi;
import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.AuthConfigLoader;
import kashi.koi.auth.OAuthTokenProvider;
import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.DefaultApiHttpClient;

// sdk facade and single entry for all API groups
public class OsuClient {

    private final BeatmapScoresApi beatmapScoresApi;

    private OsuClient(BeatmapScoresApi beatmapScoresApi) {
        this.beatmapScoresApi = beatmapScoresApi;
    }

    public static OsuClient createDefault() {
        return createDefault(AuthConfigLoader.load());
    }

    public static OsuClient createDefault(AuthConfig config) {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenProvider tokenProvider = new OAuthTokenProvider(config, httpClient, objectMapper);
        ApiHttpClient apiHttpClient = new DefaultApiHttpClient(config, tokenProvider, httpClient, objectMapper);

        return new OsuClient(new BeatmapScoresApi(apiHttpClient));
    }

    public BeatmapScoresApi beatmapScores() {
        return beatmapScoresApi;
    }

}
