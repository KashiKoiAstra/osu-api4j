package kashi.koi.model.beatmaps;

public record BeatmapsResponse(
        BeatmapExtended[] beatmaps,
        Failtimes failtimes,
        Integer maxCombo,
        String[] owners) {
}