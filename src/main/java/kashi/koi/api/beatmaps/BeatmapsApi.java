package kashi.koi.api.beatmaps;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.BeatmapExtended;
import kashi.koi.model.beatmaps.BeatmapUserScore;
import kashi.koi.model.beatmaps.BeatmapsResponse;
import kashi.koi.model.beatmaps.ScoresResponse;

import java.util.Map;

public class BeatmapsApi {

    private final ApiHttpClient httpClient;

    public BeatmapsApi(ApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * GET /beatmaps/{beatmap}
     *
     * @param beatmapId The ID of the beatmap.
     * @param query     Query parameters. Supported keys: {@code mode} (Ruleset).
     */
    public BeatmapExtended getBeatmap(Integer beatmapId, QueryMap query) {
        if (beatmapId == null) {
            throw new IllegalArgumentException("beatmapId must not be null");
        }
        return httpClient.get("/beatmaps/{id}", Map.of("id", beatmapId), query, BeatmapExtended.class);
    }

    /**
     * GET /beatmaps
     *
     * @param query Query parameters. Required: {@code ids[]} (comma-separated
     *              beatmap IDs, up to 50).
     */
    public BeatmapsResponse getBeatmaps(QueryMap query) {
        return httpClient.get("/beatmaps", Map.of(), query, BeatmapsResponse.class);
    }

    /**
     * GET /beatmaps/{beatmap}/scores/users/{user}
     *
     * @param beatmapId ID of the Beatmap.
     * @param userId    ID of the User.
     * @param query     Query parameters. Supported keys: {@code legacy_only} (0 or
     *                  1),
     *                  {@code mode} (Ruleset), {@code mods} (comma-separated mod
     *                  acronyms).
     */
    public BeatmapUserScore getUserBeatmapScore(Integer beatmapId, Integer userId, QueryMap query) {
        if (beatmapId == null) {
            throw new IllegalArgumentException("beatmapId must not be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        return httpClient.get("/beatmaps/{id}/scores/users/{user}",
                Map.of("id", beatmapId, "user", userId), query, BeatmapUserScore.class);
    }

    /**
     * GET /beatmaps/{beatmap}/scores/users/{user}/all
     *
     * @param beatmapId ID of the Beatmap.
     * @param userId    ID of the User.
     * @param query     Query parameters. Supported keys: {@code mode} (Ruleset).
     */
    public ScoresResponse getUserBeatmapScores(Integer beatmapId, Integer userId, QueryMap query) {
        if (beatmapId == null) {
            throw new IllegalArgumentException("beatmapId must not be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        return httpClient.get("/beatmaps/{id}/scores/users/{user}/all",
                Map.of("id", beatmapId, "user", userId), query, ScoresResponse.class);
    }
}
