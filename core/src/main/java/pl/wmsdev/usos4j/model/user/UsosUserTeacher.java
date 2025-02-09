package pl.wmsdev.usos4j.model.user;


import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosUserTeacher(List<UsosTeacherBasicData> items, Boolean nextPage) {

    public record UsosTeacherBasicData(Integer user_id, String match,
                                       UsosStudentProgramme[] activeStudentProgrammes,
                                       UsosEmploymentFunctionData[] activeEmploymentFunctions) {
        public record UsosEmploymentFunctionData(UsosLocalizedString function,
                                                 UsosUserEmploymentFunction.UsosUserFacultyShortData faculty) {
        }
    }
}
