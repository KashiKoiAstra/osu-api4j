package kashi.koi.http;

import java.util.Map;

public interface ApiHttpClient {
    <T> T get(String path, Map<String, String> queryParams, Class<T> responseType);

    /**
     * convenience method with path template resolution and {@link QueryMap} support
     */
    default <T> T get(String pathTemplate, Map<String, Object> pathVars, QueryMap queryMap, Class<T> responseType) {
        String path = pathTemplate;
        if (pathVars != null) {
            for (var entry : pathVars.entrySet()) {
                path = path.replace("{" + entry.getKey() + "}", String.valueOf(entry.getValue()));
            }
        }
        Map<String, String> params = queryMap != null ? queryMap.build() : Map.of();
        return get(path, params, responseType);
    }
}
