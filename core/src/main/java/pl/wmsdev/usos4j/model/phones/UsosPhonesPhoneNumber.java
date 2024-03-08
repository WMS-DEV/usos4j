package pl.wmsdev.usos4j.model.phones;

import pl.wmsdev.usos4j.model.common.UsosLocalizedString;

public record UsosPhonesPhoneNumber(String number, UsosLocalizedString comment, String phoneType) {
}
