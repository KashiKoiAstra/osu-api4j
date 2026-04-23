package kashi.koi.auth;

import java.io.IOException;
import java.util.Properties;

public class AuthConfigLoader {

    public static AuthConfig load(String resourcePath) {
        if (resourcePath == null || resourcePath.isBlank()) {
            throw new IllegalArgumentException("Resource path must be provided and cannot be blank.");
        }

        Properties properties = new Properties();

        try (var stream = AuthConfigLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) throw  new IllegalArgumentException("Resource not found: " + resourcePath);

            properties.load(stream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load auth config from resource: " + resourcePath, e);
        }

        return new AuthConfig(
                properties.getProperty("clientId"),
                properties.getProperty("clientSecret"),
                properties.getProperty("tokenUrl", "https://osu.ppy.sh/oauth/token"),
                properties.getProperty("apiBaseUrl", "https://osu.ppy.sh/api/v2"),
                properties.getProperty("scope", "public")
        );
    }

    public static AuthConfig load() {
        return load("auth.properties");
    }
}
