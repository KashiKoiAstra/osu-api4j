package kashi.koi.http;

import kashi.koi.exception.OsuApiException;

import java.util.Map;

public interface ApiHttpClient {

    <T> T get(String path, Map<String, String> queryParams, Class<T> responseType) throws OsuApiException;
}
