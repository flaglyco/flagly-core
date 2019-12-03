package co.flagly.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import co.flagly.utils.ZDT;

public class FlagTest {
    @Test void testFlagCreation() {
        UUID applicationId = UUID.randomUUID();
        ZonedDateTime now  = ZDT.now();

        Flag flag1 = Flag.of(applicationId, "test-flag-1", "Test Flag 1", true);
        Flag flag2 = Flag.of(UUID.randomUUID(), applicationId, "test-flag-2", "Test Flag 2", false, now, now);

        assertNotEquals(flag1.id(), flag2.id());
        assertEquals(flag1.applicationId(), flag2.applicationId());
        assertNotEquals(flag1.name(), flag2.name());
        assertNotEquals(flag1.description(), flag2.description());
        assertNotEquals(flag1.value(), flag2.value());
    }
}
