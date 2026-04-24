package kashi.koi.model.beatmaps;

import java.time.OffsetDateTime;


/*
 * very mysterious
 * the "id" field is said to be Integer in the official docs, but it overflows Integer, so we use Long
 */

public record Score(
        Float accuracy,
        Integer beatmapId,
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
        Mod[] mods,
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
        Integer userId
){
}
