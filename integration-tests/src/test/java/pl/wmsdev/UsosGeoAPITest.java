package pl.wmsdev;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.geo.*;

@Slf4j
public class UsosGeoAPITest extends UsosAPIBaseTest {

    @Test
    void building2() {
        log(serverApi.geo().building2(UsosGeoBuilding2Params.builder("D-20").langpref(UsosLanguage.EN).build()));
    }

    @Test
    void buildingIndex() {
        log(serverApi.geo().buildingIndex(UsosGeoBuildingIndexParams.builder().facIds("W4N", "SD1").build()));
    }

    @Test
    void room() {
        log(userApi.geo().room(UsosGeoRoomParams.builder("1991").build()));
    }

    @Test
    void buildings2() {
        log(serverApi.geo().buildings2(UsosGeoBuildings2Params.builder("F2-A", "D-1").langpref(UsosLanguage.PL).build()));
    }

    @Test
    void rooms() {
        log(userApi.geo().rooms(UsosGeoRoomsParams.builder("1991", "1315").build()));
    }
}
