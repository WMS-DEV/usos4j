package pl.wmsdev.usos4j.model.plctests;

import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosPlacementTestResult(String userId, UsosPlacementTest test, String value) implements UsosObject {
}
