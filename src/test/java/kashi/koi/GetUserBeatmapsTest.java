package kashi.koi;

import kashi.koi.http.QueryMap;
import kashi.koi.model.beatmaps.BeatmapsetExtended;

import java.util.Arrays;

public class GetUserBeatmapsTest {
    public static void main(String[] args) {
        int userId = 32164055;

        OsuClient client = OsuClient.createDefault();

        BeatmapsetExtended[] beatmaps = client.users().getUserBeatmapsets(userId, "favourite", new QueryMap());

        if (beatmaps != null) {
            System.out.println("Favourite beatmaps:");
            Arrays.stream(beatmaps).forEach(b -> System.out.println(
                    b.artistUnicode() + " - " + b.titleUnicode() + " [" + b.creator() + "]"));
        }
    }
}
