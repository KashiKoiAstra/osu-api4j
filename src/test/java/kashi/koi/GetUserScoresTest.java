package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.Score;

public class GetUserScoresTest {
    public static void main(String[] args) {

        int userId = 19970192;
        OsuClient client = OsuClient.createDefault();
        Score[] scores = client.users().getUserScores(userId, "best", new QueryMap().put("limit", "100"));

        if (scores != null) {
            for (Score score : scores) {
                System.out.printf(
                        "PP: %.2f | Accuracy: %.2f | %s (%s) - %s [%s]  \n",
                        score.pp(),
                        score.accuracy() * 100,
                        score.beatmapset().source(),
                        score.beatmapset().artist(),
                        score.beatmapset().title(),
                        score.beatmap().version());
            }
        }
    }
}
