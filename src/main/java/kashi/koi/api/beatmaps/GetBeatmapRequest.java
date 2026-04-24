package kashi.koi.api.beatmaps;

import java.util.LinkedHashMap;
import java.util.Map;

public record GetBeatmapRequest(
        String mode // how did this?
) {

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> toQueryParams() {
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (mode != null && !mode.isBlank()) {
            queryParams.put("mode", mode.trim());
        }
        return queryParams;
    }

    public static final class Builder {
        private String mode;

        public Builder mode(String mode) {
            this.mode = mode;
            return this;
        }

        public GetBeatmapRequest build() {
            return new GetBeatmapRequest(mode);
        }
    }
}
