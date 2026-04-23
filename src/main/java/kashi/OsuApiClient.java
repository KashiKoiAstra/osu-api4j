package kashi;

import com.fasterxml.jackson.databind.ObjectMapper;
import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.AuthConfigLoader;
import kashi.koi.auth.OAuthTokenProvider;

import java.net.http.HttpClient;

/*
* osu! web api v2 wrapper for Java
*
* SDK facade and single entry for all api groups
* */
public class OsuApiClient {

    // add other api group
    private OsuApiClient() {

    }

    public static OsuApiClient create() {
        return  create(AuthConfigLoader.load());
    }

    public static OsuApiClient create(AuthConfig authConfig) {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenProvider tokenProvider = new OAuthTokenProvider(authConfig, httpClient, objectMapper);

        return new OsuApiClient();
    }
}
