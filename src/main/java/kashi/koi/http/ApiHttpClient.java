package kashi.koi.http;

import java.util.Map;

public interface ApiHttpClient {

    <T> T get(String path, Map<String, String> queryParams, Class<T> responseType);
}
