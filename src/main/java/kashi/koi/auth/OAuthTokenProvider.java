package kashi.koi.auth;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OAuthTokenProvider {

    private final AuthConfig config;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final Object lock = new Object();
    private final Duration refreshThreshold = Duration.ofSeconds(10);

    private volatile Token token;

    public OAuthTokenProvider(AuthConfig config, HttpClient httpClient, ObjectMapper objectMapper) {
        this.config = config;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public OAuthTokenProvider(AuthConfig config) {
        this(config, HttpClient.newHttpClient(), new ObjectMapper());
    }

    public String getToken() {
        Token current = token;
        if (isTokenValid(current)) {
            return current.token();
        }
        synchronized (lock) {
            current = token;
            if (isTokenValid(current)) {
                return current.token();
            }
            token = requestNewToken();
            return token.token();
        }

    }

    public String refreshToken() {
        synchronized (lock) {
            token = requestNewToken();
            return token.token();
        }
    }

    public void invalidateToken() {
        token = null;
    }

    private boolean isTokenValid(Token candidate) {
        return candidate != null && candidate.isValid() && !candidate.isExpiringSoon(refreshThreshold);
    }

    private Token requestNewToken() {
        String formData = "client_id=" + encode(config.clientId()) +
                "&client_secret=" + encode(config.clientSecret()) +
                "&grant_type=client_credentials" +
                "&scope=" + encode(config.scope());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.tokenUrl()))
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("Failed to request new token", e);
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Failed to request new token: HTTP " + response.statusCode() + " - " + response.body());
        }

        TokenResponse tokenResponse;
        try {
            tokenResponse = objectMapper.readValue(response.body(), TokenResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse token response", e);
        }

        return new Token(tokenResponse.accessToken(), tokenResponse.expiresIn());
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
