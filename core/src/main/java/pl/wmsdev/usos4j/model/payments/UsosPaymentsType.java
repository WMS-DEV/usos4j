package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosPaymentsType(String id, UsosLocalizedString description) implements UsosObject {}
