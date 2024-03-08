package pl.wmsdev.usos4j.model.user;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

import java.util.List;

public record UsosUserSearchStudent(List<UsosMatchUserData> items, Boolean nextPage) {
    public record UsosMatchUserData(Integer userId, String match, List<UsosUserActiveStudentProgrammesData> activeStudentProgrammes,
                                    List<UsosUserActiveEmploymentProgrammesData> activeEmploymentFunctions) {
        public record UsosUserActiveStudentProgrammesData(UsosLocalizedString function, UsosUserProgrammsSimple faculty) {

        }

        public record UsosUserActiveEmploymentProgrammesData(UsosLocalizedString function, UsosUserProgrammsSimple faculty) {

        }
    }
}
