package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosCommonGroup(UsosGroup group, UsosGroupRole myRole,
                              UsosGroupRole hisRole) implements UsosObject {}