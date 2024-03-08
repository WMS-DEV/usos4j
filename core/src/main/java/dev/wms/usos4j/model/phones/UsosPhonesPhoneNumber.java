package dev.wms.usos4j.model.phones;

import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosPhonesPhoneNumber(String number, UsosLocalizedString comment, String phoneType) {
}
