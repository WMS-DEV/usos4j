package dev.wms.usos4j.model.theses;

import dev.wms.usos4j.model.common.UsosCommonObject;
import dev.wms.usos4j.model.fac.UsosFaculty;
import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.user.UsosUser;

import java.util.List;

public record UsosThesis(String id, UsosThesisType type, String title, UsosLocalizedString titles, UsosThesisDataLanguage language,
                         List<UsosUser> authors, List<UsosUser> supervisors, UsosFaculty faculty) implements UsosCommonObject {

    public record UsosThesisDataLanguage(String code, String isoCode) { }
}
