package kashi.koi.model.beatmaps;

public record ScoreStatistics(
        Integer countPerfect,
        Integer countGreat,
        Integer countGood,
        Integer countOk,
        Integer countMeh,
        Integer countMiss) {
}
