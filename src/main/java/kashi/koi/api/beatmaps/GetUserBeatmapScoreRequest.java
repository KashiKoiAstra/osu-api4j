package kashi.koi.api.beatmaps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


/**
 * @param legacyOnly (optional) Whether or not to exclude lazer scores. Defaults to 0.
 * @param mode       (optional) The Ruleset to get scores for.
 * @param mods       (optional) An array of matching Mods, or none.
 */
public record GetUserBeatmapScoreRequest(
        Integer legacyOnly,
        String mode,
        List<String> mods) {

    public GetUserBeatmapScoreRequest {
        if (legacyOnly != null && legacyOnly != 0 && legacyOnly != 1) {
            throw new IllegalArgumentException("legacyOnly must be 0 or 1");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static String joinMods(List<String> mods) {
        if (mods == null || mods.isEmpty()) {
            return "";
        }

        StringJoiner joiner = new StringJoiner(",");
        for (String mod : mods) {
            if (mod != null && !mod.isBlank()) {
                joiner.add(mod.trim());
            }
        }
        return joiner.toString();
    }

    public Map<String, String> toQueryParams() {
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (legacyOnly != null) {
            queryParams.put("legacy_only", String.valueOf(legacyOnly));
        }
        if (mode != null && !mode.isBlank()) {
            queryParams.put("mode", mode.trim());
        }
        String modsValue = joinMods(mods);
        if (!modsValue.isBlank()) {
            queryParams.put("mods", modsValue);
        }
        return queryParams;
    }

    public static final class Builder {
        private Integer legacyOnly = 0;
        private String mode;
        private List<String> mods;

        public Builder legacyOnly(Integer legacyOnly) {
            this.legacyOnly = legacyOnly;
            return this;
        }

        public Builder mode(String mode) {
            this.mode = mode;
            return this;
        }

        public Builder mods(List<String> mods) {
            this.mods = mods;
            return this;
        }

        public GetUserBeatmapScoreRequest build() {
            return new GetUserBeatmapScoreRequest(legacyOnly, mode, mods);
        }
    }
}
