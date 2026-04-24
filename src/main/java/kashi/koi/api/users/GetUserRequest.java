package kashi.koi.api.users;

import java.util.Map;

/**
 * @param key (optional) Type of user passed in url parameter.
 *            Can be either id or username to limit lookup by their respective type.
 *            Passing empty or invalid value will result in id lookup followed by username lookup if not found.
 *            This parameter has been deprecated.
 *            Prefix user parameter with @ instead to lookup by username.
 */
public record GetUserRequest(
        String key
) {
    public static Builder builder() {
        return new Builder();
    }

    public Map<String, String> toQueryParams() {
        return Map.of(); // No query parameters for this request
    }

    public static final class Builder {
        private String key;

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public GetUserRequest build() {
            return new GetUserRequest(key);
        }
    }
}
