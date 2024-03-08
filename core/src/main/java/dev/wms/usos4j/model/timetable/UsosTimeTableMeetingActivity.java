package dev.wms.usos4j.model.timetable;

import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@SuperBuilder
@Jacksonized
@Value
public class UsosTimeTableMeetingActivity extends UsosTimeTableActivity {
    List<String> relatedUserIds;
}
