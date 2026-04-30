package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.Score;
import kashi.koi.model.beatmaps.ScoresResponse;

public class GetUserBeatmapScoresTest {
    public static void main(String[] args) {
        int userId = 32164055;
        int beatmapId = 5540799;

        OsuClient client = OsuClient.createDefault();

        ScoresResponse scoresResponse = client.beatmaps().getUserBeatmapScores(beatmapId, userId, new QueryMap());

        if (scoresResponse != null) {
            for (Score score : scoresResponse.scores()) {
                System.out.println("Accuracy: " + String.format("%.2f", score.accuracy() * 100) + "% | pp: " + score.pp());
            }
        }
    }
}
