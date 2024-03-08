package dev.wms;

import dev.wms.usos4j.client.UsosServerAPI;
import dev.wms.usos4j.client.UsosUserAPI;
import dev.wms.usos4jheadless.HeadlessUsos;
import lombok.Data;

@Data
public final class HeadlessUsosHolder {
    public static HeadlessUsos headlessUsos;

    public static UsosUserAPI userApi() {
        return headlessUsos.usos().getUserApi(headlessUsos.accessToken());
    }

    public static UsosServerAPI serverApi() {
        return headlessUsos.usos().getServerApi();
    }

    public static void setHeadlessUsos(HeadlessUsos usos) {
        headlessUsos = usos;
    }


}
