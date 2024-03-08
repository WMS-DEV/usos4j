package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.terms.UsosTerm;

import java.util.List;
import java.util.Map;

public record UsosGroupsUser(Map<String, List<UsosGroup>> groups, List<UsosTerm> terms) implements UsosObject {}