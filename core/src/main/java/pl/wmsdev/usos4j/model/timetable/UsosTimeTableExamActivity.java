package pl.wmsdev.usos4j.model.timetable;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@SuperBuilder
@Jacksonized
public class UsosTimeTableExamActivity extends UsosTimeTableActivity {
    String courseId;
    UsosLocalizedString courseName;
    String groupNumber;
    String slotNumber;
    String buildingId;
    UsosLocalizedString buildingName;
    String roomNumber;
    String roomId;
}
