package kashi.koi;

import java.util.List;

import kashi.koi.api.beatmaps.GetUserBeatmapScoreRequest;
import kashi.koi.model.beatmaps.BeatmapUserScore;

// osu! web api v2 wrapper for Java
public class Main {
    public static void main(String[] args) {


        String userId = "32164055";
        String beatmapId = "5111622";

        OsuClient client = OsuClient.createDefault();

        GetUserBeatmapScoreRequest request = GetUserBeatmapScoreRequest.builder()
                .legacyOnly(0)
                .mode("mania")
                .mods(List.of())
                .build();

        BeatmapUserScore response = client.beatmapScores().getUserBeatmapScore(beatmapId, userId, request);

        System.out.println("position=" + response.position());
        if (response.score() != null) {
            System.out.println("score=" + response.score().score());
            System.out.println("pp=" + response.score().pp());
            System.out.println("accuracy=" + response.score().accuracy());
            System.out.println("maxCombo=" + response.score().maxCombo() + "x");
        }
    }
}