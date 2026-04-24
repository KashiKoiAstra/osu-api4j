package kashi.koi.api.users;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.model.users.KudosuHistory;

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
     * @param request URL Parameters
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
}
