package kashi.koi.model.beatmaps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Score(
        // snack_case -> camelCase
        @JsonProperty("accuracy") Float accuracy,
        @JsonProperty("beatmap_id") Integer beatmapId,
        @JsonProperty("best_id") Integer bestId,
        @JsonProperty("build_id") Integer buildId,
        @JsonProperty("classic_total_score") Integer classicTotalScore,
        @JsonProperty("ended_at")Timestamp endedAt,
        @JsonProperty("has_replay") Boolean hasReplay,
        @JsonProperty("id") Integer id,
        @JsonProperty("is_perfect_combo") Boolean isPerfectCombo,
        @JsonProperty("legacy_perfect") Boolean legacyPerfect,
        @JsonProperty("legacy_score_id") Integer legacyScoreId,
        @JsonProperty("legacy_total_score") Integer legacyTotalScore,
        @JsonProperty("max_combo") Integer maxCombo,
        @JsonProperty("maximum_statistics") ScoreStatistics scoreStatistics,
        @JsonProperty("mods") Mod[] mods,
        @JsonProperty("passed") Boolean passed,
        @JsonProperty("playlist_item_id") Integer playlistItemId,
        @JsonProperty("pp") Float pp,
        @JsonProperty("preserve") Boolean preserve,
        @JsonProperty("processed") Boolean processed,
        @JsonProperty("rank") String rank,
        @JsonProperty("ranked") Boolean ranked,
        @JsonProperty("room_id") Integer roomId,
        @JsonProperty("ruleset_id") Integer rulesetId,
        @JsonProperty("started_at") Timestamp startedAt,
        @JsonProperty("statistics") ScoreStatistics statistics,
        @JsonProperty("total_score") Integer totalScore,
        @JsonProperty("type") String type,
        @JsonProperty("user_id") Integer userId
        ) {
}
