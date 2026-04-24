package kashi.koi.model.beatmaps;


public record ScoreStatistics(
        Integer count300g,
        Integer count300,
        Integer count200,
        Integer count100,
        Integer count50,
        Integer countMiss) {
}
