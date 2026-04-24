package kashi.koi;

import kashi.koi.api.beatmaps.GetBeatmapRequest;
import kashi.koi.api.beatmaps.GetUserBeatmapScoreRequest;
import kashi.koi.api.users.GetUserRequest;
import kashi.koi.model.beatmaps.BeatmapExtended;
import kashi.koi.model.beatmaps.BeatmapUserScore;
import kashi.koi.model.users.UserExtended;

public class Main {
    public static void main(String[] args) {

        int userId = 32164055;
        int beatmapId = 5422741;

        OsuClient client = OsuClient.createDefault();

        UserExtended user = client.users().getUser(
                userId,
                null,
                GetUserRequest.builder()
                        .build()
        );

        BeatmapExtended beatmap = client.beatmaps().getBeatmap(
                beatmapId,
                GetBeatmapRequest.builder()
                        .mode("mania")
                        .build()
        );

        BeatmapUserScore score = client.beatmaps().getUserBeatmapScore(
                beatmapId,
                userId,
                GetUserBeatmapScoreRequest.builder()
                        .mode("mania")
                        .legacyOnly(0)
                        .build()
        );

        if (beatmap != null && score != null && user != null) {
            System.out.println("User: " + user.username());

            System.out.printf(
                    "%s%s%s%s%s%n",
                    beatmap.beatmapset().artist(),
                    " (" + beatmap.beatmapset().source() + ")",
                    " - ",
                    beatmap.beatmapset().title(),
                    " [" + beatmap.version() + "]"
            );
            System.out.println("Accuracy: " + String.format("%.2f%%", score.score().accuracy() * 100));
            System.out.println("PP: " + String.format("%.2f", score.score().pp()));
        }
    }
}