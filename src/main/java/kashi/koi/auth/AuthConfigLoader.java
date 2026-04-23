package kashi.koi.auth;

import java.io.IOException;
import java.util.Properties;

public class AuthConfigLoader {

    public static AuthConfig load(String resourcePath) {
        Properties props = new Properties();
        try (var stream = AuthConfigLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            props.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config from resource: " + resourcePath, e);
        }

        return new AuthConfig(
                requiredProperty(props, "clientId"),
                requiredProperty(props, "clientSecret"),
                requiredProperty(props, "tokenUrl"),
                requiredProperty(props, "apiBaseUrl"),
                props.getProperty("scope", "public").trim() // default to "public"
        );
    }

    // load from resources/data.properties
    public static AuthConfig load() {
        return load("data.properties");
    }

    private static String requiredProperty(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required property: " + key);
        }
        return value.trim();
    }
}
