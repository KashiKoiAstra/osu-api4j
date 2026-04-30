package kashi.koi.model.beatmaps;

import java.time.OffsetDateTime;

public record BeatmapsetExtended(
                Integer id,
                String artist,
                String artistUnicode,
                Covers covers,
                String creator,
                String previewUrl,
                String source,
                String status,
                String title,
                String titleUnicode,
                Integer userId,
                OffsetDateTime submittedDate,
                Integer[] ratings,
                Beatmap[] beatmaps,
                Beatmap[] converts,
                Boolean nsfw,
                Boolean video,
                Integer favouriteCount,
                Integer playCount,
                Float bpm,
                String tags,
                String description,
                Boolean storyboard,
                OffsetDateTime lastUpdated,
                OffsetDateTime rankedDate,
                Boolean canBeHyped,
                Boolean discussionEnabled,
                Boolean discussionLocked) {
}
