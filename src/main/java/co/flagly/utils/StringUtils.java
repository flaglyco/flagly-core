package co.flagly.utils;

public final class StringUtils {
    public static String escape(String s) {
        return s.replaceAll("\\\"", "\\\\\"")
            .replaceAll("\\n", "\\\\n")
            .replaceAll("\\r", "\\\\r");
    }
}
