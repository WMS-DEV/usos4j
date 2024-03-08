package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosLanguage;
import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsLecturerParams(String userId, UsosLanguage lang, boolean activeTerms) implements UsosParams {

    public static UsosGroupsLecturerParamsBuilder builder(String userId) {
        return new UsosGroupsLecturerParamsBuilder().userId(userId);
    }

}