package pl.wmsdev.usos4j.model.progs;

import pl.wmsdev.usos4j.model.common.UsosCommonObject;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFaculty;

import java.util.List;

public record UsosProgramme(String id, UsosLocalizedString name,
                            List<UsosFaculty> allFaculties, UsosLocalizedString modeOfStudies,
                            UsosLocalizedString levelOfStudies, UsosLocalizedString duration,
                            UsosLocalizedString professionalStatus, UsosProgrammeLevel level) implements UsosCommonObject {
}
