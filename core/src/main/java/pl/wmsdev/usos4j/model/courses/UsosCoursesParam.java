package pl.wmsdev.usos4j.model.courses;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosCoursesParam(String courseIds) implements UsosParams {

    public static UsosCoursesParamBuilder builder(String... courseIds) {
        String ids = FieldSelector.from(courseIds).getSelectedFields();
        return new UsosCoursesParamBuilder().courseIds(ids);
    }
}
