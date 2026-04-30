package kashi.koi.auth;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class AuthConfigLoader {

    private static final String CLASSPATH_RESOURCE = "auth.properties";

    public static AuthConfig load(Path filePath) {
        Properties props = new Properties();
        try (InputStream stream = Files.newInputStream(filePath)) {
            props.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config from file: " + filePath, e);
        }
        return fromProperties(props);
    }

    public static AuthConfig load(InputStream stream) {
        Properties props = new Properties();
        try {
            props.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config from stream", e);
        }
        return fromProperties(props);
    }

    public static AuthConfig loadFromClasspath(String resourcePath) {
        Properties props = new Properties();
        try (InputStream stream = AuthConfigLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IllegalArgumentException("Resource not found on classpath: " + resourcePath);
            }
            props.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config from classpath: " + resourcePath, e);
        }
        return fromProperties(props);
    }

    /**
     * Tries to load config in order: environment variables, then classpath
     * resource.
     * Throws if neither source provides valid credentials.
     */
    public static AuthConfig load() {
        try {
            return AuthConfig.fromEnv();
        } catch (IllegalStateException e) {
            try {
                return loadFromClasspath(CLASSPATH_RESOURCE);
            } catch (RuntimeException ex) {
                throw new IllegalStateException(
                        "Could not load osu! API credentials. Set OSU_CLIENT_ID and OSU_CLIENT_SECRET "
                                + "environment variables, or place auth.properties on the classpath. "
                                + "Alternatively, use AuthConfig.builder() to configure programmatically.",
                        ex);
            }
        }
    }

    private static AuthConfig fromProperties(Properties props) {
        return new AuthConfig(
                requiredProperty(props, "clientId"),
                requiredProperty(props, "clientSecret"),
                props.getProperty("tokenUrl", AuthConfig.DEFAULT_TOKEN_URL).trim(),
                props.getProperty("apiBaseUrl", AuthConfig.DEFAULT_API_BASE_URL).trim(),
                props.getProperty("scope", AuthConfig.DEFAULT_SCOPE).trim());
    }

    private static String requiredProperty(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required property: " + key);
        }
        return value.trim();
    }
}
