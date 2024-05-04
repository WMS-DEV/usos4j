package pl.wmsdev.usos4j.model.calendar;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.fac.UsosFacultySimple;

public record UsosCalendarEvent(String id, UsosLocalizedString name, String startDate, String endDate,
                                UsosFacultySimple faculty, String type, boolean isDayOff) {
}
