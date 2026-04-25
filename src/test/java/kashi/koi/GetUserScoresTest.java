package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.Score;

public class GetUserScoresTest {
    public static void main(String[] args) {
        int userId = 32164055;

        OsuClient client = OsuClient.createDefault();

        Score[] scores = client.users().getUserScores(userId, "best", new QueryMap().put("limit", "10"));

        if (scores != null) {
            for (Score score : scores) {

                System.out.printf(
                        "%s (%s) - %s [%s] | PP: %.2f | Accuracy: %.2f \n",
                        score.beatmapset().source(),
                        score.beatmapset().artist(),
                        score.beatmapset().title(),
                        score.beatmap().version(),
                        score.pp(),
                        score.accuracy() * 100);
            }
        }
    }
}
