package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGradeTypesParam(String gradeTypeIds) implements UsosParams {

    public static UsosGradeTypesParamBuilder builder(String... gradeTypeIds) {
        String ids = FieldSelector.from(gradeTypeIds).getSelectedFields();
        return new UsosGradeTypesParamBuilder().gradeTypeIds(ids);
    }

}
