package co.flagly.utils;

import com.google.gson.*;
import dev.akif.e.E;
import dev.akif.e.gson.EGsonAdapter;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public final class JsonUtils {
    public static <A> String toJson(A a) {
        return gson.toJson(a);
    }

    public static <A> A fromJson(String json, Class<A> c) {
        return gson.fromJson(json, c);
    }

    private static class ZonedDateTimeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
        @Override public JsonElement serialize(ZonedDateTime zdt, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(ZDT.toString(zdt));
        }

        @Override public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return ZDT.fromString(json.getAsString());
        }
    }

    private static Gson gson = new GsonBuilder()
        .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
        .registerTypeAdapter(E.class, new EGsonAdapter())
        .create();
}
