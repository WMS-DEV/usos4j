package pl.wmsdev.usos4j.model.terms;

import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTermIndexParams(UsosTermType termType, Boolean activeOnly) implements UsosParams {}