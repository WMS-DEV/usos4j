package pl.wmsdev.usos4j.model.progs;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosStageParam(String id) implements UsosParams {

	public static UsosStageParamBuilder builder(String id) {
		return new UsosStageParamBuilder().id(id);
	}
}
