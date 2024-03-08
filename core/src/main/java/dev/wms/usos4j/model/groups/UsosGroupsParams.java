package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsParams(String groupIds) implements UsosParams {

    public static UsosGroupsParamsBuilder builder(String groupIds) {
        return new UsosGroupsParamsBuilder().groupIds(groupIds);
    }

    public static UsosGroupsParamsHelper helper() {
        return new UsosGroupsParamsHelper();
    }

}
