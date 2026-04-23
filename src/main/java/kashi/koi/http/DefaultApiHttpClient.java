package kashi.koi.http;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kashi.koi.auth.AuthConfig;
import kashi.koi.auth.OAuthTokenProvider;
import kashi.koi.exception.OsuApiException;

public class DefaultApiHttpClient implements ApiHttpClient {

    private final AuthConfig config;
    private final OAuthTokenProvider tokenProvider;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public DefaultApiHttpClient(AuthConfig config, OAuthTokenProvider tokenProvider, HttpClient httpClient,
                                ObjectMapper objectMapper) {
        this.config = config;
        this.tokenProvider = tokenProvider;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T get(String path, Map<String, String> queryParams, Class<T> responseType) {
        URI uri = buildUri(path, queryParams);
        HttpResponse<String> response = sendGetWithAuthRetry(uri);

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new OsuApiException(
                    "Request failed: GET " + uri + " status=" + response.statusCode(),
                    response.statusCode(),
                    response.body());
        }

        try {
            return objectMapper.readValue(response.body(), responseType);
        } catch (JsonProcessingException e) {
            throw new OsuApiException("Failed to parse response from " + uri, e);
        }
    }

    private HttpResponse<String> sendGetWithAuthRetry(URI uri) {
        HttpResponse<String> first = sendGet(uri, tokenProvider.getToken());
        if (first.statusCode() != 401) {
            return first;
        }

        tokenProvider.invalidateToken();
        return sendGet(uri, tokenProvider.getToken());
    }

    private HttpResponse<String> sendGet(URI uri, String token) {
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
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new OsuApiException("HTTP request failed: GET " + uri, e);
        }
    }

    private URI buildUri(String path, Map<String, String> queryParams) {
        String normalizedBase = config.apiBaseUrl().endsWith("/")
                ? config.apiBaseUrl().substring(0, config.apiBaseUrl().length() - 1)
                : config.apiBaseUrl();
        String normalizedPath = path.startsWith("/") ? path : "/" + path;

        if (queryParams == null || queryParams.isEmpty()) {
            return URI.create(normalizedBase + normalizedPath);
        }

        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isBlank()) {
                continue;
            }
            joiner.add(encode(entry.getKey()) + "=" + encode(entry.getValue()));
        }

        String query = joiner.toString();
        if (query.isBlank()) {
            return URI.create(normalizedBase + normalizedPath);
        }

        return URI.create(normalizedBase + normalizedPath + "?" + query);
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
