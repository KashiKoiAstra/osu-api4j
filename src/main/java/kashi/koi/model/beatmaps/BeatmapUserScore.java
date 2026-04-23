package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// data module
// attention : camelCase -> snake_case mapping

@JsonIgnoreProperties(ignoreUnknown = true)
public record BeatmapUserScore(
        @JsonProperty("position") Integer position,
        @JsonProperty("score") Score score
) {
}
