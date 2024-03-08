package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFaculty;

public record UsosRanking (String code, UsosLocalizedString name, UsosLocalizedString description,
                           Boolean isSummary, String courseGroupFlag, String rankingCode,
                           UsosFaculty faculty, String precision) implements UsosObject {
}
