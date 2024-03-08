package dev.wms.usos4j.model.groups;

import java.util.HashSet;
import java.util.Set;

public class UsosGroupsParamsHelper {

    private final Set<String> groups = new HashSet<>();

    public UsosGroupsParamsHelper addGroup(Integer courseUnitId, Integer groupNumber) {
        groups.add(courseUnitId + "," + groupNumber);
        return this;
    }

    public UsosGroupsParams build() {
        String groupIds = "(" + String.join("|", groups) + ")";
        return UsosGroupsParams.builder(groupIds).build();
    }

}
