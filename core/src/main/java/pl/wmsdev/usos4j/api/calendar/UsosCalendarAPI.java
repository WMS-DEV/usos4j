package pl.wmsdev.usos4j.api.calendar;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.calendar.UsosCalendarEvent;
import pl.wmsdev.usos4j.utils.UsosDateUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
                "faculty_id", List.of(facultyId), "start_date", List.of(UsosDateUtils.formatAsDate(startDate)),
                "end_date", List.of(UsosDateUtils.formatAsDate(endDate)))), UsosCalendarEvent[].class));
    }
}
