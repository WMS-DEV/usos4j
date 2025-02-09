package pl.wmsdev.usos4j.model.registrations;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFacultySimple;

public record UsosRegistration(String id, UsosLocalizedString description, UsosLocalizedString message,
                               String type, String status, UsosFacultySimple faculty, Boolean isLinkageRequired,
                               String wwwInstance)
        implements UsosObject {
}
