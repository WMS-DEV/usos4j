package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;

public record UsosPaymentsType(String id, UsosLocalizedString description) implements UsosObject {}
