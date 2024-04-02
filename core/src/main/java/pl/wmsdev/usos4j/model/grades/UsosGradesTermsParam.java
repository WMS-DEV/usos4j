package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;
import pl.wmsdev.usos4j.utils.StringUtils;

@Builder
public record UsosGradesTermsParam(String termIds, String courseIds) implements UsosParams {

    public static UsosGradesTermsParamBuilder builder(String termId, String... termIds) {
        String ids = StringUtils.arrayToPipelinedString(termId, termIds);
        return new UsosGradesTermsParamBuilder().termIds(ids);
    }
}
