package kashi.koi.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public class TokenStorage implements TokenStore {

    private static final Path DEFAULT_DIR = Path.of(System.getProperty("user.home"), ".osu-api4j");

    private final Path storagePath;
    private final ObjectMapper objectMapper;

    public TokenStorage(Path storagePath, ObjectMapper objectMapper) {
        this.storagePath = storagePath;
        this.objectMapper = objectMapper;
    }

    public TokenStorage(ObjectMapper objectMapper) {
        this(defaultPath(), objectMapper);
    }

    public static Path defaultPath() {
        return DEFAULT_DIR.resolve("token.json");
    }

    @Override
    public Token load() {
        if (!Files.exists(storagePath)) {
            return null;
        }
        try {
            PersistedToken pt = objectMapper.readValue(storagePath.toFile(), PersistedToken.class);
            if (pt.accessToken == null || pt.accessToken.isBlank()) {
                return null;
            }
            return new Token(pt.accessToken, Instant.ofEpochMilli(pt.expiresAtMillis));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void save(Token token) {
        try {
            Files.createDirectories(storagePath.getParent());
            objectMapper.writeValue(storagePath.toFile(),
                    new PersistedToken(token.token(), token.expiresAt().toEpochMilli()));
        } catch (IOException ignored) {
        }
    }

    @Override
    public void clear() {
        try {
            Files.deleteIfExists(storagePath);
        } catch (IOException ignored) {
        }
    }

    private record PersistedToken(String accessToken, long expiresAtMillis) {
    }
}
