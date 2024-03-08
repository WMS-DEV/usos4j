package dev.wms.usos4j.model.groups;

import dev.wms.usos4j.model.common.UsosLanguage;
import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsParticipantParams(UsosLanguage lang, boolean activeTerms) implements UsosParams {}