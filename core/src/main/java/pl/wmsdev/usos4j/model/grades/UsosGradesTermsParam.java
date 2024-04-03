package pl.wmsdev.usos4j.model.grades;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGradesTermsParam(String termIds, String courseIds) implements UsosParams {

    public static UsosGradesTermsParamBuilder builder(String... termIds) {
        String ids = FieldSelector.from(termIds).getSelectedFields();
        return new UsosGradesTermsParamBuilder().termIds(ids);
    }
}
