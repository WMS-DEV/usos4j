package dev.wms.usos4j.model.calendar;

import dev.wms.usos4j.model.fac.UsosFaculty;
import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosCalendarEvent(String id, UsosLocalizedString name, String startDate, String endDate,
                                UsosFaculty faculty, String type, boolean isDayOff) {
}
