package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.courses.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsosCourseAPITest extends UsosAPIBaseTest {

    @Test
    void classTypesIndex() {
        log(serverApi.courses().classTypesIndex());
    }

    @Test
    void course() {
        var result = serverApi.courses().course(UsosCourseParam.builder("W13IST-SI0002G").build());
        log("result: " + result);
        log("temrs: " + Arrays.toString(result.terms()));
    }

    @Test
    @Disabled("PWr does not support course2")
    void course2() {
        log(serverApi.courses().course2(UsosCourseParam.builder("W13IST-SI0002G").build()));
    }

    @Test
    void courseEdition() {
        log(userApi.courses().courseEdition(UsosCourseEditionParam.builder("W13IST-SI0002G", "2023/24-Z").build()));
    }

    @Test
    void courseEdition2() {
        log(userApi.courses().courseEdition2(UsosCourseEditionParam.builder("W13IST-SI0002G", "2023/24-Z").build()));
    }

    @Test
    void courseUnit() {
        log(userApi.courses().courseUnit(UsosCourseUnitParam.builder("52818").build()));
    }

    @Test
    void coursesSingleArgument() {
        log(userApi.courses().courses(UsosCoursesParam.builder("W13IST-SI0002G").build()));
    }

    @Test
    void coursesMultipleArguments() {
        log(userApi.courses().courses(UsosCoursesParam.builder("W13IST-SI0002G", "W04IST-SI0005G").build()));
    }

    @Test
    void coursesNoArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            log(userApi.courses().courses(UsosCoursesParam.builder().build()));
        });
    }

    @Test
    void isParticipant() {
        log(userApi.courses().isParticipant(UsosCourseIsParticipantParam.builder("W13IST-SI0002G", "2023/24-Z").build()));
        log(userApi.courses().isParticipant(UsosCourseIsParticipantParam.builder("W13IST-SI0002G", "2022/23-Z").build()));
    }

    @Test
    void search() {
        log(serverApi.courses().search(UsosCourseSearchParam.builder(UsosLanguage.PL).name("chemia").build()));
    }

    @Test
    void unit() {
        log(userApi.courses().unit(UsosUnitParam.builder("52818").build()));
    }

    @Test
    void unitsSingleArgument() {
        log(userApi.courses().units(UsosUnitsParam.builder("52818").build()));
    }

    @Test
    void unitsMultipleArguments() {
        log(userApi.courses().units(UsosUnitsParam.builder("52818", "52813").build()));
    }

    @Test
    void unitsNoArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            log(userApi.courses().units(UsosUnitsParam.builder().build()));
        });
    }

    @Test
    void user() {
        log(userApi.courses().user());
    }

    @Test
    void userEctsPoints() {
        log(userApi.courses().userEctsPoints());
    }

}
