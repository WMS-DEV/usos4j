package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.grades.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void gradeTypesSingleArgument() {
        log(serverApi.grades().gradeTypes(UsosGradeTypesParam.builder("PWR").build()));
    }

    @Test
    void gradeTypesMultipleArguments() {
        log(serverApi.grades().gradeTypes(UsosGradeTypesParam.builder("PWR", "ECTS", "PWR-OLD").build()));
    }

    @Test
    void gradeTypesNoArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            log(serverApi.grades().gradeTypes(UsosGradeTypesParam.builder().build()));
        });
    }

    @Test
    @Disabled("no access")
    void latest() {
        log(userApi.grades().latest(UsosLatestParam.builder().days(7).build()));
    }

    @Test
    void terms2SingleArgument() {
        log(userApi.grades().terms2(UsosGradesTermsParam.builder("2023/24-Z").build()));
    }

    @Test
    void terms2MultipleArguments() {
        log(userApi.grades().terms2(UsosGradesTermsParam.builder("2023/24-Z", "2023/24-L").build()));
    }

    @Test
    void terms2NoArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            log(serverApi.grades().gradeTypes(UsosGradeTypesParam.builder().build()));
        });
    }
}
