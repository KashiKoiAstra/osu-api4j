package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
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

