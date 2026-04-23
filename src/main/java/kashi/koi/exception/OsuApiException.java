package kashi.koi.exception;

public class OsuApiException extends RuntimeException {
    private final int statusCode;
    private final String responseBody;

    public OsuApiException(String message) {
        super(message);
        this.statusCode = -1;
        this.responseBody = null;
    }

    public OsuApiException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
        this.responseBody = null;
    }

    public OsuApiException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int statusCode() {
        return statusCode;
    }

    public String responseBody() {
        return responseBody;
    }
}
