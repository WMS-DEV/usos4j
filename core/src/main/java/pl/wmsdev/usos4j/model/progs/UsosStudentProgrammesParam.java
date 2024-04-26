package pl.wmsdev.usos4j.model.progs;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosStudentProgrammesParam(String studentProgrammeIds) implements UsosParams {

	public static UsosStudentProgrammesParamBuilder builder(String... studentProgrammeIds) {
		String ids = FieldSelector.from(studentProgrammeIds).getSelectedFields();
		return new UsosStudentProgrammesParamBuilder().studentProgrammeIds(ids);
	}
}
