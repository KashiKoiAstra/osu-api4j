package kashi.koi.api.beatmaps;

import kashi.koi.http.ApiHttpClient;
import kashi.koi.model.beatmaps.BeatmapUserScore;

/*
* */
public class BeatmapScoresApi {
    private final ApiHttpClient httpClient;

    public BeatmapScoresApi(ApiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

}
