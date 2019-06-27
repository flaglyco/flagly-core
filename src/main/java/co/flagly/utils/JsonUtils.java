package co.flagly.utils;

import co.flagly.core.FlaglyError;
import com.google.gson.*;

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

    private static class FlaglyErrorAdapter implements JsonSerializer<FlaglyError>, JsonDeserializer<FlaglyError> {
        @Override public JsonElement serialize(FlaglyError error, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("code", error.code());
            obj.addProperty("message", error.message());
            if (error.causeMessage() != null) {
                obj.addProperty("causeMessage", error.causeMessage());
            }
            return obj;
        }

        @Override public FlaglyError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            int code = obj.getAsJsonPrimitive("code").getAsInt();
            String message = obj.getAsJsonPrimitive("message").getAsString();
            String causeMessage = obj.getAsJsonPrimitive("causeMessage").getAsString();
            return FlaglyError.of(code, message, causeMessage);
        }
    }

    private static Gson gson = new GsonBuilder()
        .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
        .registerTypeAdapter(FlaglyError.class, new FlaglyErrorAdapter())
        .create();
}
