package pl.wmsdev.usos4j.model.cards;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosCardsMcard(UsosLocalizedString whyCannotOrder, UsosLocalizedString whyCannotRevoke, Object orders) {
}
