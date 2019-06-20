package co.flagly.core;

import java.util.Objects;
import java.util.StringJoiner;

public final class FlaglyError extends Exception {
    public static final short CLIENT_ERROR_CODE = 400;
    public static final short SERVER_ERROR_CODE = 500;

    private final int code;
    private final String message;
    private final Throwable cause;

    private FlaglyError(int code,
                       String message,
                       Throwable cause) {
        this.code    = code;
        this.message = message;
        this.cause   = cause;
    }

    public static FlaglyError of(int code, String message, Throwable cause) {
        return new FlaglyError(code, message, cause);
    }

    public static FlaglyError of(int code, String message) {
        return new FlaglyError(code, message, null);
    }

    public static FlaglyError of(String message) {
        return new FlaglyError(SERVER_ERROR_CODE, message, null);
    }

    public static FlaglyError of(String message, Throwable cause) {
        return new FlaglyError(SERVER_ERROR_CODE, message, cause);
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override public String getMessage() {
        return message();
    }

    @Override public synchronized Throwable fillInStackTrace() {
        if (cause == null) {
            // Not calling `super.fillInStackTrace()` so stack trace is not filled.
            return this;
        }

        return super.fillInStackTrace();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FlaglyError)) return false;

        FlaglyError error = (FlaglyError) o;

        return code == error.code &&
               message.equals(error.message) &&
               cause == null ? error.cause == null : cause.equals(error.getCause());
    }

    @Override public int hashCode() {
        return Objects.hash(code, message, cause);
    }

    @Override public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}")
            .add("\"code\":" + code)
            .add("\"message\":\"" + message + "\"");

        if (cause != null) {
            joiner.add("\"cause\":\"" + cause.getMessage() + "\"");
        }

        return joiner.toString();
    }
}
