package pl.wmsdev;

import lombok.Data;
import pl.wmsdev.usos4j.client.UsosServerAPI;
import pl.wmsdev.usos4j.client.UsosUserAPI;
import pl.wmsdev.usos4jheadless.HeadlessUsos;

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
