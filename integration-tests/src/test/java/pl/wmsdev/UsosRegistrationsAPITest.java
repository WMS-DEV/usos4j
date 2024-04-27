package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.registrations.*;

import java.time.LocalDate;

public class UsosRegistrationsAPITest extends UsosAPIBaseTest{

    @Test
    @Disabled("PWr does not support this request, 500 - internal server error")
    void courseRegistrationRounds() {
        log(userApi.registrations().courseRegistrationRounds(UsosCourseRegistrationRoundsParams.builder("W04-IST-SI-1-23/24Z")
                .build()
        ));
    }

    @Test
    void ranking() {
        log(userApi.registrations().ranking(UsosRankingParams.builder("1").build()));
    }

    @Test
    @Disabled("PWr temporarily grants no access")
    void coursesCart() {
        log(userApi.registrations().coursesCart());
    }

    @Test
    void userRegistrations() {
        log(userApi.registrations().userRegistrations(UsosUserRegistrationsParams.builder().build()));
    }

    @Test
    void facultyRegistrations() {
        log(userApi.registrations().facultyRegistrations(UsosFacultyRegistrationsParams.builder("W4N").build()));
    }

    @Test
    void facultyTokenRegistrations() {
        log(userApi.registrations().facultyTokenRegistrations(UsosFacultyTokenRegistrationsParams.builder("W4N").build()));
    }

    @Test
    void registration() {
        log(userApi.registrations().registration(UsosRegistrationParams.builder("W04-IST-SI-1-23/24Z").build()));
    }

    @Test
    void registrationCourse() {
        log(userApi.registrations().registrationCourse(UsosRegistrationCourseParams.builder("W04-IST-SI-1-23/24Z",
                "W04IST-SI0013L",
                "2023/24-Z")
                .build()
        ));
    }

    @Test
    @Disabled("PWr does not support this request, 500 - internal server error")
    void searchRounds() {
        log(userApi.registrations().searchRounds(UsosSearchRoundsParams.builder(
                LocalDate.of(2023, 9, 25),
                LocalDate.of(2023, 10, 10))
                .build()
        ));
    }

    @Test
    @Disabled("PWr does not support this request, 500 - internal server error")
    void registrationRound() {
        log(userApi.registrations().registrationRound(UsosRegistrationRoundParams.builder("W04-IST-SI-1-23/24Z").build()));
    }

    @Test
    @Disabled("PWr does not support this request, 500 - internal server error")
    void registrationRoundCourses() {
        log(userApi.registrations().registrationRoundCourses(UsosRegistrationRoundCoursesParams.builder("W04-IST-SI-1-23/24Z").build()));
    }

    @Test
    void searchTokenRound() {
        log(userApi.registrations().searchTokenRounds(UsosSearchTokenRoundsParams.builder(
                LocalDate.of(2023, 9, 25),
                LocalDate.of(2023, 10, 10))
                .build()
        ));
    }

    @Test
    @Disabled("No data: no valid id to check")
    void tokenRegistration() {
        log(userApi.registrations().tokenRegistration(UsosTokenRegistrationParams.builder("1").build()));
    }

    @Test
    void tokenRegistrationCourse() {
        log(userApi.registrations().tokenRegistrationCourse(UsosTokenRegistrationCourseParams.builder("W04-IST-SI-1-23/24Z",
                "W04IST-SI0013L",
                "2023/24-Z").build()));
    }

    @Test
    void tokenRegistrationRound() {
        log(userApi.registrations().tokenRegistrationRound(UsosTokenRegistrationRoundParams.builder("1").build()));
    }

    @Test
    void userTokenRegistrations() {
        log(userApi.registrations().userTokenRegistrations(UsosUserTokenRegistrationsParams.builder().build()));
    }
}
