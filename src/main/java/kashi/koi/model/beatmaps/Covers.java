package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonProperty;

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
