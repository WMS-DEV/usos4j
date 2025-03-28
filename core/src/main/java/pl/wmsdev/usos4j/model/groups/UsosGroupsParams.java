package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGroupsParams(String groupIds) implements UsosParams {

    public static UsosGroupsParamsBuilder builder(String groupIds) {
        return new UsosGroupsParamsBuilder().groupIds(groupIds);
    }

    public static UsosGroupsParamsHelper helper() {
        return new UsosGroupsParamsHelper();
    }

}
