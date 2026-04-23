package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModSetting(
        @JsonProperty("name") String name,
        @JsonProperty("value") Object value
) {
}
