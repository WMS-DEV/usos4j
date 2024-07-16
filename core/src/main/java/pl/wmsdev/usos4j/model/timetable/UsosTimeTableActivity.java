package pl.wmsdev.usos4j.model.timetable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Jacksonized
@Getter
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UsosTimeTableClassGroupActivity.class, names = {"classgroup2", "classgroup"}),
        @JsonSubTypes.Type(value = UsosTimeTableMeetingActivity.class, name = "meeting"),
        @JsonSubTypes.Type(value = UsosTimeTableExamActivity.class, names = "exam")
})
public class UsosTimeTableActivity {
    String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime endTime;
    UsosLocalizedString name;
    String url;
}
