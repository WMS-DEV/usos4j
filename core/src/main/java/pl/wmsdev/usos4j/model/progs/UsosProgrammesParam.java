package pl.wmsdev.usos4j.model.progs;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosProgrammesParam(String programmeIds) implements UsosParams {

    public static UsosProgrammesParamBuilder builder(String... programmeIds) {
        String ids = FieldSelector.from(programmeIds).getSelectedFields();
        return new UsosProgrammesParamBuilder().programmeIds(ids);
    }
}
