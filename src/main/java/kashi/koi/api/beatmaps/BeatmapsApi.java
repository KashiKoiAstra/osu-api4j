package kashi.koi.api.beatmaps;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.model.beatmaps.BeatmapExtended;
import kashi.koi.model.beatmaps.BeatmapUserScore;

public class BeatmapsApi {

    private final ApiHttpClient httpClient;

    public BeatmapsApi(ApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Return a User's score on a Beatmap
     * GET /beatmaps/{beatmap}/scores/users/{user}
     *
     * @param beatmapId ID of the Beatmap.
     * @param userId    ID of the User.
     * @param request   Query Parameters
     * @return a BeatmapUserScore object containing the user's score for the specified beatmaps, or null if not found
     */
    public BeatmapUserScore getUserBeatmapScore(Integer beatmapId, Integer userId, GetUserBeatmapScoreRequest request) {
        if (beatmapId == null) {
            throw new IllegalArgumentException("beatmapId must be a non-empty string");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId must be a non-empty string");
        }

        GetUserBeatmapScoreRequest effectiveRequest = request == null
                ? GetUserBeatmapScoreRequest.builder().build()
                : request;

        String path = "/beatmaps/" + beatmapId + "/scores/users/" + userId;
        return httpClient.get(path, effectiveRequest.toQueryParams(), BeatmapUserScore.class);
    }

    /**
     * Gets beatmap data for the specified beatmap ID.
     * GET /beatmaps/{beatmap}
     *
     * @param beatmapId The ID of the beatmap.
     * @param request   Query Parameters
     * @return a BeatmapExtended object containing the beatmaps data, or null if not found
     */
    public BeatmapExtended getBeatmap(Integer beatmapId, GetBeatmapRequest request) {
        if (beatmapId == null) {
            throw new IllegalArgumentException("beatmapId must be a non-empty string");
        }

        GetBeatmapRequest effectiveRequest = request == null
                ? GetBeatmapRequest.builder().build()
                : request;

        String path = "/beatmaps/" + beatmapId;
        return httpClient.get(path, effectiveRequest.toQueryParams(), BeatmapExtended.class);
    }
}
