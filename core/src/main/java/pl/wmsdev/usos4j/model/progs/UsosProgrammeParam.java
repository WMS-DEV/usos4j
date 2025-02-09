package pl.wmsdev.usos4j.model.progs;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosProgrammeParam(String programmeId) implements UsosParams {

    public static UsosProgrammeParamBuilder builder(String programmeId) {
        return new UsosProgrammeParamBuilder().programmeId(programmeId);
    }
}
