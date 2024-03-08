package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosGroupUser(String id, String firstName, String lastName, String userId) implements UsosObject {}
