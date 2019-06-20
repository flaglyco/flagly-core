package co.flagly.core;

import co.flagly.utils.ZDT;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public final class Flag {
    private final UUID id;
    private final String name;
    private final String description;
    private final boolean value;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    private Flag(UUID id,
                 String name,
                 String description,
                 boolean value,
                 ZonedDateTime createdAt,
                 ZonedDateTime updatedAt) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.value       = value;
        this.createdAt   = createdAt;
        this.updatedAt   = updatedAt;
    }

    public static Flag of(UUID id,
                          String name,
                          String description,
                          boolean value,
                          ZonedDateTime createdAt,
                          ZonedDateTime updatedAt) {
        return new Flag(id, name, description, value, createdAt, updatedAt);
    }

    public static Flag of(String name, String description, boolean value) {
        return new Flag(UUID.randomUUID(), name, description, value, ZDT.now(), ZDT.now());
    }

    public UUID id() {
        return id;
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
               name.equals(flag.name) &&
               description.equals(flag.description) &&
               value == flag.value &&
               createdAt.equals(flag.createdAt) &&
               updatedAt.equals(flag.updatedAt);
    }

    @Override public int hashCode() {
        return Objects.hash(id, name, description, value, createdAt, updatedAt);
    }

    @Override public String toString() {
        return new StringJoiner(", ", "{", "}")
            .add("\"id\":\"" + id + "\"")
            .add("\"name\":\"" + name + "\"")
            .add("\"description\":\"" + description + "\"")
            .add("\"value\":\"" + value)
            .add("\"createdAt\":\"" + ZDT.toString(createdAt) + "\"")
            .add("\"updatedAt\":\"" + ZDT.toString(updatedAt) + "\"")
            .toString();
    }
}
