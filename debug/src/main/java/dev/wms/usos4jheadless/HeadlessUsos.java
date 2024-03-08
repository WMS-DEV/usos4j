package dev.wms.usos4jheadless;

import dev.wms.usos4j.client.Usos;
import dev.wms.usos4j.model.auth.UsosAccessToken;

public record HeadlessUsos(UsosAccessToken accessToken, Usos usos) {
}
