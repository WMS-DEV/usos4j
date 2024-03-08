package pl.wmsdev.usos4j.model.payments;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;

public record UsosPaymentsCurrency(String id, UsosLocalizedString name) implements UsosObject {}
