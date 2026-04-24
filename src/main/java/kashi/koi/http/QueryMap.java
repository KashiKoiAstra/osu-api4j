package kashi.koi.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// fluent & null-safe builder for query parameters

// NOTE: use the exact osu! API parameter names
// null params will be skipped
public class QueryMap {

    private final Map<String, String> params = new LinkedHashMap<>();

    public QueryMap put(String key, Object value) {
        if (key != null && !key.isBlank() && value != null) {
            String s = value.toString();
            if (!s.isBlank()) {
                params.put(key, s.trim());
            }
        }
        return this;
    }

    public QueryMap putList(String key, List<?> values) {
        if (key != null && !key.isBlank() && values != null && !values.isEmpty()) {
            String joined = values.stream()
                    .filter(v -> v != null && !v.toString().isBlank())
                    .map(v -> v.toString().trim())
                    .collect(Collectors.joining(","));
            if (!joined.isEmpty()) {
                params.put(key, joined);
            }
        }
        return this;
    }

    Map<String, String> build() {
        return params.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(params);
    }
}
