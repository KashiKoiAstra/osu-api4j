package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.BeatmapExtended;
import kashi.koi.model.beatmaps.BeatmapUserScore;
import kashi.koi.model.users.UserExtended;

public class ScoreQueryTest {
    public static void main(String[] args) {

        int userId = 32164055;
        int beatmapId = 5422741;

        OsuClient client = OsuClient.createDefault();

        UserExtended user = client.users().getUser(userId, null, null);

        BeatmapExtended beatmap = client.beatmaps().getBeatmap(beatmapId,
                new QueryMap().put("mode", "mania"));

        BeatmapUserScore score = client.beatmaps().getUserBeatmapScore(beatmapId, userId,
                new QueryMap()
                        .put("mode", "mania")
                        .put("legacy_only", 0));

        if (beatmap != null && score != null && user != null) {
            System.out.println("User: " + user.username());

            System.out.printf(
                    "%s%s%s%s%s%n",
                    beatmap.beatmapset().artist(),
                    " (" + beatmap.beatmapset().source() + ")",
                    " - ",
                    beatmap.beatmapset().title(),
                    " [" + beatmap.version() + "]");
            System.out.println("Accuracy: " + String.format("%.2f%%", score.score().accuracy() * 100));
            System.out.println("PP: " + String.format("%.2f", score.score().pp()));
        }
    }
}
