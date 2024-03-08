package dev.wms.usos4j.model.timetable;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@SuperBuilder
@Jacksonized
public class UsosTimeTableClassGroupActivity extends UsosTimeTableActivity {
    String courseId;
    UsosLocalizedString courseName;
    UsosLocalizedString classtypeName;
    List<String> lecturerIds;
    String groupNumber;
    String classgroupProfileUrl;
    UsosLocalizedString buildingName;
    String buildingId;
    String roomNumber;
    String roomId;
    String unitId;
    String classtypeId;
    String cgwmId;
}
