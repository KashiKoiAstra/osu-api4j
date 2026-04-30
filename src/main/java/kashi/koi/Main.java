package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.BeatmapExtended;
import kashi.koi.model.beatmaps.BeatmapsResponse;

public class Main {

    public static void main(String[] args) {

        OsuClient client = OsuClient.createDefault();

        BeatmapsResponse response = client.beatmaps().getBeatmaps(
                new QueryMap().put("ids[]", "5582125"));

        if (response != null) {
            for (BeatmapExtended beatmap : response.beatmaps()) {
                System.out.println(beatmap.version() + " | " + beatmap.beatmapset().artist() + " - "
                        + beatmap.beatmapset().title());
            }
        }
    }
}
