package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.housing.*;

public class UsosHousingAPITest extends UsosAPIBaseTest {

    @Test
    void dormitories() {
        log(userApi.housing().dormitories(UsosDormitoriesParam.builder("27","28").build()));
    }


    @Test
    void dormroomType() {
        log(userApi.housing().dormroomType(UsosDormroomTypeParam.builder("Mod_S").build()));
    }

    @Test
    void residentType() {
        log(userApi.housing().residentType(UsosResidentTypeParam.builder("S").build()));
    }

    @Test
    void allocationPeriods() {
        log(userApi.housing().allocationPeriods(UsosAllocationPeriodsParam.builder("Wakacje_2023").build()));
    }

    @Test
    void dormroomTypes() {
        log(userApi.housing().dormroomTypes(UsosDormroomTypesParam.builder("Mod_S").build()));
    }

    @Test
    void residentTypes() {
        log(userApi.housing().residentTypes(UsosResidentTypesParam.builder("S","E").build()));
    }

    @Test
    void dormitory() {
        log(userApi.housing().dormitory(UsosDormitoryParam.builder("28").build()));
    }

    @Test
    void dormitoryIndex(){
        log(userApi.housing().dormitoryIndex());
    }

    @Test
    void residentTypeIndex(){
        log(userApi.housing().residentTypeIndex());
    }

    @Test
    void allocationPeriodIndex(){
        log(userApi.housing().allocationPeriodIndex());
    }

    @Test
    void dormroomTypeIndex() {
        log(userApi.housing().dormroomTypeIndex());
    }

    @Test
    void allocationPeriod(){
        log(userApi.housing().allocationPeriod(UsosAllocationPeriodParam.builder("1").build()));
    }
}
