package pl.wmsdev.usos4j.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import pl.wmsdev.usos4j.model.exceptions.Usos4jException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class UsosDeserializationUtils {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.debug(e.toString());
            throw new Usos4jException("Error while deserializing JSON: %s".formatted(json));
        }
    }

}
