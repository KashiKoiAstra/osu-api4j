package kashi.koi.model.beatmaps;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Score(
        @JsonProperty("id") Long id,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("accuracy") Double accuracy,
        @JsonProperty("score") Long score,
        @JsonProperty("max_combo") Integer maxCombo,
        @JsonProperty("passed") Boolean passed,
        @JsonProperty("pp") Double pp,
        @JsonProperty("rank") String rank,
        @JsonProperty("mods") List<String> mods,
        @JsonProperty("statistics") ScoreStatistics statistics) {
}
