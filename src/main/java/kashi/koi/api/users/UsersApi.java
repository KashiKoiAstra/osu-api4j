package kashi.koi.api.users;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.http.QueryMap;
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
     * @param query  Query parameters. Supported keys: {@code limit} (int), {@code offset} (string).
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
     * @param mode   (optional) Ruleset. User default mode will be used if not specified.
     * @param query  Query parameters. The {@code key} parameter has been deprecated —
     *               prefix the user parameter with {@code @} to look up by username instead.
     */
    public UserExtended getUser(Integer userId, String mode, QueryMap query) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }
        String effectiveMode = mode != null && !mode.isBlank() ? mode.trim() : "";
        return httpClient.get("/users/{user}/{mode}",
                Map.of("user", userId, "mode", effectiveMode), query, UserExtended.class);
    }
}
