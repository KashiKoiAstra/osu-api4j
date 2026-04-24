package kashi.koi.api.users;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.model.users.KudosuHistory;
import kashi.koi.model.users.UserExtended;

public class UsersApi {
    private final ApiHttpClient httpClient;

    public UsersApi(ApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Returns kudosu history.
     * GET /users/{user}/kudosu
     *
     * @param userId  ID of the user.
     * @param request Query Parameters
     * @return Array of KudosuHistory.
     */
    public KudosuHistory[] getUserKudosu(Integer userId, GetUserKudosuRequest request) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }

        GetUserKudosuRequest effectiveRequest = request == null
                ? GetUserKudosuRequest.builder().build()
                : request;

        String path = "/users/" + userId + "/kudosu";
        return httpClient.get(path, effectiveRequest.toQueryParams(), KudosuHistory[].class);
    }

    /**
     * This endpoint returns the detail of specified user.
     * GET /users/{user}/{mode?}
     *
     * @param userId  ID or @-prefixed username of the user. Previous usernames are also checked in some cases.
     * @param mode    (optional) Ruleset. User default mode will be used if not specified.
     * @param request Query Parameters
     */
    public UserExtended getUser(Integer userId, String mode, GetUserRequest request) {
        if (userId == null) {
            throw new IllegalArgumentException("userId must not be null");
        }

        GetUserRequest getUserRequest = request == null
                ? GetUserRequest.builder().build()
                : request;

        String path = "/users/" + userId + "/" + (mode != null && !mode.isBlank() ? mode.trim() : "");

        return httpClient.get(path, getUserRequest.toQueryParams(), UserExtended.class);
    }
}
