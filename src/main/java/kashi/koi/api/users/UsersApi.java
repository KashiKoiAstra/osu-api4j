package kashi.koi.api.users;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.BeatmapsetExtended;
import kashi.koi.model.beatmaps.Score;
import kashi.koi.model.users.BeatmapPlaycount;
import kashi.koi.model.users.KudosuHistory;
import kashi.koi.model.users.UserExtended;

import java.util.Map;

public class UsersApi {
    private final ApiHttpClient httpClient;

    public UsersApi(ApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * GET /users/{user}/kudosu
     *
     * @param userId ID of the user.
     * @param query  Query parameters. Supported keys: {@code limit} (int),
     *               {@code offset} (string).
     */
    public KudosuHistory[] getUserKudosu(Integer userId, QueryMap query) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        return httpClient.get("/users/{user}/kudosu", Map.of("user", userId), query, KudosuHistory[].class);
    }

    /**
     * GET /users/{user}/{mode?}
     *
     * @param userId ID or @-prefixed username of the user.
     * @param mode   (optional) Ruleset. User default mode will be used if not
     *               specified.
     * @param query  Query parameters. The {@code key} parameter has been deprecated
     *               —
     *               prefix the user parameter with {@code @} to look up by username
     *               instead.
     */
    public UserExtended getUser(Integer userId, String mode, QueryMap query) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        String effectiveMode = mode != null && !mode.isBlank() ? mode.trim() : "";
        return httpClient.get("/users/{user}/{mode}",
                Map.of("user", userId, "mode", effectiveMode), query, UserExtended.class);
    }

    /**
     * GET /users/{user}/scores/{type}
     *
     * @param userId ID of the user.
     * @param type   Score type. Must be one of these: {@code best}, {@code firsts},
     *               {@code recent}.
     * @param query  Query parameters:
     *               (optional) {@code Integer legacy_only} defaults to 0
     *               (optional) {@code Integer include_fails} defaults to 0
     *               (optional) {@code String mode}
     *               (optional) {@code Integer limit} Maximum number of results.
     *               (optional) {@code String offset} Result offset for pagination.
     */
    public Score[] getUserScores(Integer userId, String type, QueryMap query) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type must not be null or blank");
        }
        return httpClient.get("/users/{user}/scores/{type}",
                Map.of("user", userId, "type", type.trim()), query, Score[].class);
    }

    /**
     * GET /users/{user}/beatmapsets/most_played
     *
     * @param userId ID of the user.
     * @param query  Query parameters. Supported keys: {@code limit} (int),
     *               {@code offset} (int).
     */
    public BeatmapPlaycount[] getUserMostPlayedBeatmaps(Integer userId, QueryMap query) {
        if (userId == null)
            throw new IllegalArgumentException("userId must not be null");
        return httpClient.get("/users/{user}/beatmapsets/most_played",
                Map.of("user", userId), query, BeatmapPlaycount[].class);
    }

    /**
     * GET /users/{user}/beatmapsets/{type}
     *
     * @param userId ID of the user.
     * @param type   Beatmapset type. Must be one of:
     *               {@code favourite},
     *               {@code graveyard}, {@code loved}, {@code pending},
     *               {@code ranked},
     *               {@code guest}, {@code nominated}.
     * @param query  Query parameters. Supported keys: {@code limit} (int),
     *               {@code offset} (int).
     */
    public BeatmapsetExtended[] getUserBeatmapsets(Integer userId, String type, QueryMap query) {
        if (userId == null)
            throw new IllegalArgumentException("userId must not be null");
        if (type == null || type.isBlank())
            throw new IllegalArgumentException("type must not be null or blank");
        return httpClient.get("/users/{user}/beatmapsets/{type}",
                Map.of("user", userId, "type", type.trim()), query, BeatmapsetExtended[].class);
    }
}
