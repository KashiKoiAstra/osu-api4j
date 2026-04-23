package kashi.koi.exception;

public class OsuApiException extends Exception{

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

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
