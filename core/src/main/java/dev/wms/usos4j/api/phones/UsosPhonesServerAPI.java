package dev.wms.usos4j.api.phones;

import dev.wms.usos4j.model.phones.UsosPhonesPhoneType;

public interface UsosPhonesServerAPI {
    UsosPhonesPhoneType phoneType(String id);
}
