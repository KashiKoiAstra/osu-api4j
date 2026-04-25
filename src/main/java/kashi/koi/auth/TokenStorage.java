package kashi.koi.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

class TokenStorage {

    private final Path storagePath;
    private final ObjectMapper objectMapper;

    TokenStorage(Path storagePath, ObjectMapper objectMapper) {
        this.storagePath = storagePath;
        this.objectMapper = objectMapper;
    }

    static Path defaultPath() {
        return Paths.get("src", "main", "resources", "token.json");
    }

    Token load() {
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

    void save(Token token) {
        try {
            Files.createDirectories(storagePath.getParent());
            objectMapper.writeValue(storagePath.toFile(),
                    new PersistedToken(token.token(), token.expiresAt().toEpochMilli()));
        } catch (IOException ignored) {
        }
    }

    private record PersistedToken(String accessToken, long expiresAtMillis) {
    }
}
