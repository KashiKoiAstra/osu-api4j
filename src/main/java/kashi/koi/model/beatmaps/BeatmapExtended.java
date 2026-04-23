package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.OffsetDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record BeatmapExtended(
        Float accuracy,
        float ar,
        Integer id,
        Integer beatmapsetId,
        Float bpm,
        Boolean convert,
        Integer countCircles,
        Integer countSliders,
        Integer countSpinners,
        Float cs,
        OffsetDateTime deletedAt,
        Float drain,
        Integer hitLength,
        Boolean isScoreable,
        OffsetDateTime lastUpdated,
        String mode,
        Integer modeInt,
        @JsonProperty("passcount") Integer passCount,
        @JsonProperty("playcount") Integer playCount,
        Integer ranked,
        String status,
        Integer totalLength,
        String url,
        String version,
        Beatmapset beatmapset,
        Failtimes failtimes,
        Integer maxCombo
) {}

