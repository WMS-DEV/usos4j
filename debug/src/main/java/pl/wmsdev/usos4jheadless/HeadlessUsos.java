package pl.wmsdev.usos4jheadless;

import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;

public record HeadlessUsos(UsosAccessToken accessToken, Usos usos) {
}
