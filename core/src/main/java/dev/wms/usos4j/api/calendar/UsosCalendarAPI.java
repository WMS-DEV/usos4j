package dev.wms.usos4j.api.calendar;

import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.client.ScribeOAuthRequestFactory;
import dev.wms.usos4j.client.UsosUserAPIDefinition;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.calendar.UsosCalendarEvent;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static dev.wms.usos4j.utils.UsosDateUtils.formatAsDate;

public class UsosCalendarAPI extends UsosUserAPIDefinition {

    public UsosCalendarAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public UsosCalendarEvent calendarEvent(String eventId) {
        return requestWithAccessToken(requestFactory.get("services/calendar/calendar_event", Map.of("fields",
                List.of("id", "name", "start_date", "end_date", "faculty", "type", "is_day_off"),
                "id", List.of(eventId))), UsosCalendarEvent.class);
    }

    public List<UsosCalendarEvent> search(String facultyId, LocalDateTime startDate, LocalDateTime endDate) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/calendar/search", Map.of("fields",
                List.of("id", "name", "start_date", "end_date", "faculty", "type", "is_day_off"),
                "faculty_id", List.of(facultyId), "start_date", List.of(formatAsDate(startDate)),
                "end_date", List.of(formatAsDate(endDate)))), UsosCalendarEvent[].class));
    }
}
