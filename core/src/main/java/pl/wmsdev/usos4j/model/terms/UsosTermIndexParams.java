package pl.wmsdev.usos4j.model.terms;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosTermIndexParams(UsosTermType termType, Boolean activeOnly) implements UsosParams {}