package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.Score;

public class GetUserScoresTest {
    public static void main(String[] args) {

        int userId = 19970192;

        OsuClient client = OsuClient.builder()
                .clientId("52276")
                .clientSecret("Se2L4HGAnXm8uI4wCOtRQxMFjzHKemnTTj5ulGst")
                .build();

        Score[] scores = client.users().getUserScores(userId, "best", new QueryMap().put("limit", "20"));

        if (scores != null) {
            for (Score score : scores) {
                System.out.printf(
                        "PP: %.2f | Accuracy: %.2f | %s (%s) - %s [%s]  \n",
                        score.pp(),
                        score.accuracy() * 100,
                        score.beatmapset().source(),
                        score.beatmapset().artistUnicode(),
                        score.beatmapset().titleUnicode(),
                        score.beatmap().version());
            }
        }
    }
}
