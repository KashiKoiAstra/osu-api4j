package kashi.koi.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.OAuthTokenProvider;
import kashi.koi.exception.OsuApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public class DefaultApiHttpClient implements ApiHttpClient {

    private final AuthConfig config;
    private final OAuthTokenProvider tokenProvider;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public DefaultApiHttpClient(AuthConfig config, HttpClient httpClient, ObjectMapper objectMapper) {
        this.config = config;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.tokenProvider = new OAuthTokenProvider(config, httpClient, objectMapper);
    }

    @Override
    public <T> T get(String path, java.util.Map<String, String> queryParams, Class<T> responseType) throws OsuApiException {
        URI uri = buildUri(path, queryParams);
        HttpResponse<String> response = sendGetResponseWithAuthRetry(uri);

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new OsuApiException("GET request to " + uri +
                            " failed with status code " + response.statusCode() +
                            ": " + response.body());
        }

        try {
            return objectMapper.readValue(response.body(), responseType);
        } catch (JsonProcessingException e) {
            throw new OsuApiException("Failed to parse response from " + uri);
        }
    }

    private HttpResponse<String> sendGetRequest(URI uri, String token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) Thread.currentThread().interrupt();
            throw new RuntimeException("Failed to send GET request to " + uri, e);
        }
    }

    private HttpResponse<String> sendGetResponseWithAuthRetry(URI uri) {
        HttpResponse<String> first = sendGetRequest(uri, tokenProvider.getToken());
        if (first.statusCode() != 401) {
            return first;
        }

        tokenProvider.invalidateToken();
        return sendGetRequest(uri, tokenProvider.getToken());
    }

    private URI buildUri(String path, Map<String ,String> queryParams) {
        String uriBase = config.apiBaseUrl().endsWith("/")
                ? config.apiBaseUrl().substring(0, config.apiBaseUrl().length() - 1)
                : config.apiBaseUrl();

        String uriPath = path.startsWith("/") ? path : "/" + path;

        if (queryParams == null || queryParams.isEmpty()) {
            return URI.create(uriBase + uriPath);
        }

        StringJoiner queryJoiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isBlank()) continue; // skip null or blank values

            queryJoiner.add(encode(entry.getKey()) + "=" + encode(entry.getValue()));
        }

        String query = queryJoiner.toString();
        if (query.isBlank()) return URI.create(uriBase + uriPath);
        return URI.create(uriBase + uriPath + "?" + query);
    }

    private static String encode(String val) {
        return URLEncoder.encode(val, StandardCharsets.UTF_8);
    }
}
