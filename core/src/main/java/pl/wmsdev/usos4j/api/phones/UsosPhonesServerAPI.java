package pl.wmsdev.usos4j.api.phones;

import pl.wmsdev.usos4j.model.phones.UsosPhonesPhoneType;

public interface UsosPhonesServerAPI {
    UsosPhonesPhoneType phoneType(String id);
}
