package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.users.BeatmapPlaycount;

import java.util.Arrays;

public class UserMostPlayedBeatmapsTest {
    public static void main(String[] args) {
        int userId = 32164055;

        OsuClient client = OsuClient.createDefault();

        BeatmapPlaycount[] beatmaps = client.users().getUserMostPlayedBeatmaps(userId, new QueryMap());

        if (beatmaps != null) {
            System.out.println("Most played beatmaps:");
            Arrays.stream(beatmaps).forEach(b -> System.out.println(
                    b.beatmapset().artistUnicode() + " - " + b.beatmapset().titleUnicode() + " ["
                            + b.beatmapset().creator() + "]"
                            + " | Playcount: " + b.count()));
        }
    }
}
