package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsParticipantParams(UsosLanguage lang, boolean activeTerms) implements UsosParams {}