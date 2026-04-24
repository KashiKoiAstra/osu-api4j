package kashi.koi.api.users;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @param limit  (optional) Maximum number of results.
 * @param offset (optional) Result offset for pagination.
 */
public record GetUserKudosuRequest(
        Integer limit,
        String offset
) {
    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> toQueryParams() {
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (limit != null) {
            queryParams.put("limit", String.valueOf(limit));
        }
        if (offset != null && !offset.isBlank()) {
            queryParams.put("offset", offset.trim());
        }
        return queryParams;
    }

    public static final class Builder {
        private Integer limit;
        private String offset;

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder offset(String offset) {
            this.offset = offset;
            return this;
        }

        public GetUserKudosuRequest build() {
            return new GetUserKudosuRequest(limit, offset);
        }
    }
}
