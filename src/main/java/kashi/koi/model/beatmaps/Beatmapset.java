package kashi.koi.model.beatmaps;


import java.time.OffsetDateTime;

public record Beatmapset(
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
        Integer[] ratings
) {
}

