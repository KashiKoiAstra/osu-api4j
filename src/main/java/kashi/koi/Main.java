package kashi.koi;

import kashi.koi.api.beatmaps.GetUserBeatmapScoreRequest;
import kashi.koi.model.beatmaps.BeatmapUserScore;

// osu! web api v2 wrapper for Java
public class Main {
    public static void main(String[] args) {
        String userId = "32164055";
        String beatmapId = "5510352";

        OsuClient client = OsuClient.createDefault();

        GetUserBeatmapScoreRequest request = GetUserBeatmapScoreRequest.builder()
                .mode("mania")
                .build();

        BeatmapUserScore response = client.beatmapScores().getUserBeatmapScore(beatmapId, userId, request);

        System.out.println("position=" + response.position());
        if (response.score() != null) {
            System.out.println("score=" + response.score().score());
            System.out.println("accuracy=" + String.format("%.2f", response.score().accuracy() * 100) + "%");
            System.out.println("maxCombo=" + response.score().maxCombo() + "x");
            System.out.println("pp=" + String.format("%.2f", response.score().pp()));
        }
    }
}