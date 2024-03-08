package dev.wms.usos4j.model.plctests;

import dev.wms.usos4j.model.common.UsosObject;

public record UsosPlacementTestResult(String userId, UsosPlacementTest test, String value) implements UsosObject {
}
