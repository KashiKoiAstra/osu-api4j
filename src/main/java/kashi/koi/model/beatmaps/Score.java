package kashi.koi.model.beatmaps;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Score(
        Float accuracy,
        Beatmap beatmap,
        Beatmapset beatmapset,
        @JsonProperty("beatmap_id") Integer beatmapId,
        Integer bestId,
        Integer buildId,
        Integer classicTotalScore,
        OffsetDateTime endedAt,
        Boolean hasReplay,
        Long id,
        Boolean isPerfectCombo,
        Boolean legacyPerfect,
        Integer legacyScoreId,
        Integer legacyTotalScore,
        Integer maxCombo,
        ScoreStatistics maximumStatistics,
        String[] mods,
        Boolean passed,
        Integer playlistItemId,
        Float pp,
        Boolean preserve,
        Boolean processed,
        String rank,
        Boolean ranked,
        Integer roomId,
        Integer rulesetId,
        OffsetDateTime startedAt,
        ScoreStatistics statistics,
        Integer totalScore,
        String type,
        Integer userId) {
}
