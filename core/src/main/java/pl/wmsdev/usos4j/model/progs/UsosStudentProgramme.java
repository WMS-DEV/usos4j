package pl.wmsdev.usos4j.model.progs;

import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.time.LocalDate;
import java.util.List;

public record UsosStudentProgramme(String id, UsosUser user, UsosProgramme programme,
                                   UsosProgrammeStatus status, LocalDate admissionDate,
                                   List<UsosStage> stages, Boolean isPrimary) implements UsosObject {
}
