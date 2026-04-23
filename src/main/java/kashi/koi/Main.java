package kashi.koi;

import kashi.koi.api.beatmaps.GetBeatmapRequest;
import kashi.koi.model.beatmaps.BeatmapExtended;

// osu! web api v2 wrapper for Java
public class Main {
    public static void main(String[] args) {
        String userId = "32164055";
        String beatmapId = "5510352";

        OsuClient client = OsuClient.createDefault();

        GetBeatmapRequest request = GetBeatmapRequest.builder()
                .mode("mania")
                .build();

        BeatmapExtended response = client.beatmap().getBeatmap(beatmapId, request);

        if (response != null) {
            System.out.println("Beatmap Title: " + response.beatmapset().title());
            System.out.println("Artist: " + response.beatmapset().artist());
            System.out.println("Creator: " + response.beatmapset().creator());
        }
    }
}