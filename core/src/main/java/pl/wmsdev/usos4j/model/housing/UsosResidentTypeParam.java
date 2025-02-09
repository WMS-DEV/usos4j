package pl.wmsdev.usos4j.model.housing;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosResidentTypeParam(String residentTypeId) implements UsosParams {

    public static UsosResidentTypeParamBuilder builder(String residentTypeId) {
        return new UsosResidentTypeParamBuilder().residentTypeId(residentTypeId);
    }
}
