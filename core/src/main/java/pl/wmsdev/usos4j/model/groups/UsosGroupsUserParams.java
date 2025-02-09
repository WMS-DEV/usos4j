package pl.wmsdev.usos4j.model.groups;

import lombok.Builder;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;

@Builder
public record UsosGroupsUserParams(UsosLanguage lang, Boolean activeTerms) implements UsosParams {}