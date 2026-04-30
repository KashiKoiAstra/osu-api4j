package kashi.koi.model.users;

import kashi.koi.model.beatmaps.Beatmap;
import kashi.koi.model.beatmaps.Beatmapset;

public record BeatmapPlaycount(
        Integer beatmapId,
        Beatmap beatmap,
        Beatmapset beatmapset,
        Integer count
) {
}
