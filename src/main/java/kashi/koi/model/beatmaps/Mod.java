package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Mod(
        @JsonProperty("acronym") String acronym,
        @JsonProperty("settings") ModSetting[] settings
) {

}
