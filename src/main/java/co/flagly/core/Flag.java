package co.flagly.core;

import co.flagly.utils.JsonUtils;
import co.flagly.utils.ZDT;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Flag {
    private final UUID id;
    private final UUID applicationId;
    private final String name;
    private final String description;
    private final boolean value;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    private Flag(UUID id,
                 UUID applicationId,
                 String name,
                 String description,
                 boolean value,
                 ZonedDateTime createdAt,
                 ZonedDateTime updatedAt) {
        this.id            = id;
        this.applicationId = applicationId;
        this.name          = name;
        this.description   = description;
        this.value         = value;
        this.createdAt     = createdAt;
        this.updatedAt     = updatedAt;
    }

    public static Flag of(UUID id,
                          UUID applicationId,
                          String name,
                          String description,
                          boolean value,
                          ZonedDateTime createdAt,
                          ZonedDateTime updatedAt) {
        return new Flag(id, applicationId, name, description, value, createdAt, updatedAt);
    }

    public static Flag of(UUID applicationId, String name, String description, boolean value) {
        return new Flag(UUID.randomUUID(), applicationId, name, description, value, ZDT.now(), ZDT.now());
    }

    public UUID id() {
        return id;
    }

    public UUID applicationId() {
        return applicationId;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public boolean value() {
        return value;
    }

    public ZonedDateTime createdAt() {
        return createdAt;
    }

    public ZonedDateTime updatedAt() {
        return updatedAt;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Flag)) return false;

        Flag flag = (Flag) o;

        return id.equals(flag.id) &&
               applicationId.equals(flag.applicationId) &&
               name.equals(flag.name) &&
               description.equals(flag.description) &&
               value == flag.value &&
               createdAt.equals(flag.createdAt) &&
               updatedAt.equals(flag.updatedAt);
    }

    @Override public int hashCode() {
        return Objects.hash(id, applicationId, name, description, value, createdAt, updatedAt);
    }

    @Override public String toString() {
        return JsonUtils.toJson(this);
    }
}
