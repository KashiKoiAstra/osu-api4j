package kashi.koi.api.beatmaps;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.model.beatmaps.BeatmapUserScore;

public class BeatmapScoresApi {

    private final ApiHttpClient httpClient;

    public BeatmapScoresApi(ApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public BeatmapUserScore getUserBeatmapScore(String beatmapId, String userId, GetUserBeatmapScoreRequest request) {
        if (beatmapId == null || beatmapId.isEmpty()) {
            throw new IllegalArgumentException("beatmapId must be a non-empty string");
        }
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("userId must be a non-empty string");
        }

        GetUserBeatmapScoreRequest effectiveRequest = request == null
                ? GetUserBeatmapScoreRequest.builder().build()
                : request;

        String path = "/beatmaps/" + beatmapId + "/scores/users/" + userId;
        return httpClient.get(path, effectiveRequest.toQueryParams(), BeatmapUserScore.class);
    }
}
