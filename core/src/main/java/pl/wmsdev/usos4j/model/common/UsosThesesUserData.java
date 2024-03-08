package pl.wmsdev.usos4j.model.common;

import pl.wmsdev.usos4j.model.theses.UsosThesis;

import java.util.List;

public record UsosThesesUserData(List<UsosThesis> authoredTheses) implements UsosObject {
}
