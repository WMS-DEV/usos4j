package pl.wmsdev.usos4j.unit;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.exceptions.Usos4jException;
import pl.wmsdev.usos4j.model.terms.UsosTerm;
import pl.wmsdev.usos4j.model.timetable.UsosTimeTableActivity;
import pl.wmsdev.usos4j.model.timetable.UsosTimeTableMeetingActivity;
import pl.wmsdev.usos4j.model.user.UsosUser;
import pl.wmsdev.usos4j.utils.UsosDeserializationUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UsosJsonFixtureTest {

    @Test
    void readsDatesLocalizedStringsAndUnknownFields() throws IOException {
        var term = UsosDeserializationUtils.deserialize(fixture("term.json"), UsosTerm.class);
        assertEquals("2026Z", term.id());
        assertEquals(LocalDate.of(2026, 10, 1), term.startDate());
        assertEquals(LocalDate.of(2027, 2, 28), term.finishDate());
        assertEquals("Semestr zimowy", term.name().pl());
        assertEquals("Winter semester", term.name().en());
        assertEquals(42, term.orderKey());
        assertTrue(term.isActive());
    }

    @Test
    void readsUnicodeNullAndNestedRecords() throws IOException {
        var user = UsosDeserializationUtils.deserialize(fixture("user.json"), UsosUser.class);
        assertEquals("Żaneta", user.firstName());
        assertEquals(LocalDate.of(2000, 2, 29), user.birthDate());
        assertNull(user.email());
        assertNull(user.studentStatus());
        assertFalse(user.hasEmail());
        assertEquals(LocalDate.of(2020, 1, 1), user.previousNames()[0].until());
        assertEquals("Środa", user.officeHours().pl());
    }

    @Test
    void readsGenericListsAndMaps() throws IOException {
        var json = fixture("term.json");
        List<UsosTerm> terms = UsosDeserializationUtils.deserialize("[" + json + "]", new TypeReference<>() {});
        Map<String, UsosTerm> indexed = UsosDeserializationUtils.deserialize(
                "{\"2026Z\":" + json + "}", new TypeReference<>() {});
        assertEquals("2026Z", terms.get(0).id());
        assertEquals(terms.get(0), indexed.get("2026Z"));
    }

    @Test
    void readsPolymorphicTimetableAndDateTimes() throws IOException {
        var activities = UsosDeserializationUtils.deserialize(fixture("timetable.json"), UsosTimeTableActivity[].class);
        assertEquals(1, activities.length);
        var meeting = assertInstanceOf(UsosTimeTableMeetingActivity.class, activities[0]);
        assertEquals(LocalDateTime.of(2026, 10, 28, 10, 15), meeting.getStartTime());
        assertEquals(LocalDateTime.of(2026, 10, 28, 11, 0), meeting.getEndTime());
        assertEquals(List.of("42", "43"), meeting.getRelatedUserIds());
    }

    @Test
    void preservesEmptyGenericContainersAndNullValues() {
        List<UsosTerm> terms = UsosDeserializationUtils.deserialize("[]", new TypeReference<>() {});
        Map<String, UsosTerm> indexed = UsosDeserializationUtils.deserialize("{\"missing\":null}", new TypeReference<>() {});
        assertTrue(terms.isEmpty());
        assertTrue(indexed.containsKey("missing"));
        assertNull(indexed.get("missing"));
    }

    @Test
    void rejectsInvalidJsonAndInvalidDatesWithOriginalCause() {
        var invalidJson = assertThrows(Usos4jException.class,
                () -> UsosDeserializationUtils.deserialize("{", UsosTerm.class));
        var invalidDate = assertThrows(Usos4jException.class,
                () -> UsosDeserializationUtils.deserialize("{\"start_date\":\"not-a-date\"}", UsosTerm.class));
        assertNotNull(invalidJson.getCause());
        assertNotNull(invalidDate.getCause());
    }

    static String fixture(String name) throws IOException {
        try (var stream = UsosJsonFixtureTest.class.getResourceAsStream("/fixtures/" + name)) {
            assertNotNull(stream, "Missing fixture: " + name);
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
