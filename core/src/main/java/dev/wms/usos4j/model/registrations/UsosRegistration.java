package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.fac.UsosFaculty;

public record UsosRegistration (String id, UsosLocalizedString description, UsosLocalizedString message,
                                String type, String status, UsosFaculty faculty, Boolean isLinkageRequired,
                                String wwwInstance)
                                implements UsosObject {
}
