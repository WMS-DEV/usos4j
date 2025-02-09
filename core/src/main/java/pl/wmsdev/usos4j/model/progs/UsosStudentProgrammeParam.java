package pl.wmsdev.usos4j.model.progs;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosStudentProgrammeParam(String studentProgrammeId) implements UsosParams {

    public static UsosStudentProgrammeParamBuilder builder(String studentProgrammeId) {
        return new UsosStudentProgrammeParamBuilder().studentProgrammeId(studentProgrammeId);
    }
}
