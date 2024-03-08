package dev.wms.usos4j.model.terms;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTermIndexParams(UsosTermType termType, Boolean activeOnly) implements UsosParams {}