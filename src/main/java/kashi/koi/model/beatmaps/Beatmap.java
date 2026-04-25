package kashi.koi.model.beatmaps;

public record Beatmap(
                Integer beatmapsetId,
                Float difficultyRating,
                Integer id,
                String mode,
                String status,
                Integer totalLength,
                Integer userId,
                String version) {
}
