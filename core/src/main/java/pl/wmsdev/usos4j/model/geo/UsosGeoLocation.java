package pl.wmsdev.usos4j.model.geo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The variable name {@code longitude} corresponds to {@code long} in the USOS API.
 * {@code long} could not be used here because it is a reserved keyword in Java.
 */
public record UsosGeoLocation(@JsonProperty("long") Double longitude, Double lat) {
}
