package co.flagly.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public final class ZDT {
    public static ZonedDateTime now() {
        return ZonedDateTime.now().withFixedOffsetZone().withNano(0);
    }

    public static String toString(ZonedDateTime zdt) {
        return zdt.withFixedOffsetZone().withNano(0).format(formatter);
    }

    public static ZonedDateTime fromString(String zdt) {
        return ZonedDateTime.parse(zdt, formatter).withFixedOffsetZone().withNano(0);
    }

    private static DateTimeFormatter formatter =
        new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .optionalStart()
            .appendOffsetId()
            .toFormatter();
}
