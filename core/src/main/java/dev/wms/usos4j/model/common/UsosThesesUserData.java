package dev.wms.usos4j.model.common;

import dev.wms.usos4j.model.theses.UsosThesis;

import java.util.List;

public record UsosThesesUserData(List<UsosThesis> authoredTheses) implements UsosObject {
}
