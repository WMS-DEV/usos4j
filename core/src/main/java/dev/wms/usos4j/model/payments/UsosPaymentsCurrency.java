package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;

public record UsosPaymentsCurrency(String id, UsosLocalizedString name) implements UsosObject {}
