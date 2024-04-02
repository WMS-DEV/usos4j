package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.grades.*;

public class UsosGradesAPITest extends UsosAPIBaseTest {

    @Test
    void courseEdition2() {
        log(userApi.grades().courseEdition2(UsosGradesCourseEditionParams.builder("W13IST-SI0002G", "2023/24-Z").build()));
    }

    @Test
    void exam() {
        log(userApi.grades().exam(UsosGradesExamParam.builder(36373).build()));
    }

    @Test
    void grade() {
        log(userApi.grades().grade(UsosGradeParam.builder(36373, 1).build()));
    }

    @Test
    void gradeType() {
        log(serverApi.grades().gradeType(UsosGradeTypeParam.builder("PWR").build()));
    }

    @Test
    void gradeTypeIndex() {
        log(serverApi.grades().gradeTypeIndex());
    }

    @Test
    void gradeTypesSingle() {
        log(serverApi.grades().gradeTypes(UsosGradeTypesParam.builder("PWR").build()));
    }

    @Test
    void gradeTypesMultiple() {
        log(serverApi.grades().gradeTypes(UsosGradeTypesParam.builder("PWR", "ECTS", "PWR-OLD").build()));
    }

    @Test
    @Disabled("no access")
    void latest() {
        log(userApi.grades().latest(UsosLatestParam.builder().days(7).build()));
    }

    @Test
    void terms2() {
        log(userApi.grades().terms2(UsosGradesTermsParam.builder("2023/24-Z").build()));
    }
}
