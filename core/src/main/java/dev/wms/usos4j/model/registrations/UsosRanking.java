package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.fac.UsosFaculty;

public record UsosRanking (String code, UsosLocalizedString name, UsosLocalizedString description,
                           Boolean isSummary, String courseGroupFlag, String rankingCode,
                           UsosFaculty faculty, String precision) implements UsosObject {
}
