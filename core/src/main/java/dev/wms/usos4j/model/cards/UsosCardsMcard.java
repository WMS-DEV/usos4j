package dev.wms.usos4j.model.cards;

import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosCardsMcard(UsosLocalizedString whyCannotOrder, UsosLocalizedString whyCannotRevoke, Object orders) {
}
