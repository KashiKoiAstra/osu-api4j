package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record Covers(
        String cover,
        @JsonProperty("cover@2x") String coverAt2x,
        String card,
        @JsonProperty("card@2x") String cardAt2x,
        String list,
        @JsonProperty("list@2x") String listAt2x,
        String slimcover,
        @JsonProperty("slimcover@2x") String slimcoverAt2x
        )
{
}
