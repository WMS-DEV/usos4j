package pl.wmsdev.usos4j.model.progs;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosProgrammeSearchParam(UsosLanguage lang, String query, int number, int start) implements UsosParams {

	public static UsosProgrammeSearchParamBuilder builder(UsosLanguage lang) {
		return new UsosProgrammeSearchParamBuilder().lang(lang);
	}
}
