package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;
import pl.wmsdev.usos4j.utils.StringUtils;

@Builder
public record UsosGradeTypesParam(String gradeTypeIds) implements UsosParams {

    public static UsosGradeTypesParamBuilder builder(String gradeTypeId, String... gradeTypeIds) {
        String ids = StringUtils.arrayToPipelinedString(gradeTypeId, gradeTypeIds);
        return new UsosGradeTypesParamBuilder().gradeTypeIds(ids);
    }

}
