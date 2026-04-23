package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ScoreStatistics(
        @JsonProperty("count_300") Integer count300,
        @JsonProperty("count_100") Integer count100,
        @JsonProperty("count_50") Integer count50,
        @JsonProperty("count_miss") Integer countMiss) {
}
