package co.flagly.core;

import co.flagly.utils.JsonUtils;

import java.util.Objects;

public final class FlaglyError extends Exception {
    public static final short CLIENT_ERROR_CODE = 400;
    public static final short SERVER_ERROR_CODE = 500;

    private final int code;
    private final String message;
    private final String causeMessage;

    private FlaglyError(int code,
                        String message,
                        String causeMessage) {
        this.code         = code;
        this.message      = message;
        this.causeMessage = causeMessage;
    }

    private FlaglyError(int code,
                        String message,
                        Throwable cause) {
        this.code         = code;
        this.message      = message;
        this.causeMessage = cause.getMessage();
        initCause(cause);
    }

    public static FlaglyError of(int code, String message, Throwable cause) {
        return new FlaglyError(code, message, cause);
    }

    public static FlaglyError of(int code, String message, String causeMessage) {
        return new FlaglyError(code, message, causeMessage);
    }

    public static FlaglyError of(int code, String message) {
        return new FlaglyError(code, message, (String) null);
    }

    public static FlaglyError of(String message, Throwable cause) {
        return new FlaglyError(SERVER_ERROR_CODE, message, cause);
    }

    public static FlaglyError of(String message, String causeMessage) {
        return new FlaglyError(SERVER_ERROR_CODE, message, causeMessage);
    }

    public static FlaglyError of(String message) {
        return new FlaglyError(SERVER_ERROR_CODE, message, (String) null);
    }

    public static FlaglyError of(Throwable cause) {
        return new FlaglyError(SERVER_ERROR_CODE, cause.getMessage(), cause);
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public String causeMessage() {
        return causeMessage;
    }

    @Override public String getMessage() {
        return message();
    }

    @Override public synchronized Throwable fillInStackTrace() {
        if (getCause() == null) {
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
               causeMessage == null ? error.causeMessage == null : causeMessage.equals(error.getCause().getMessage());
    }

    @Override public int hashCode() {
        return Objects.hash(code, message, causeMessage);
    }

    @Override public String toString() {
        return JsonUtils.toJson(this);
    }
}
