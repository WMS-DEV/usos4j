package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsosUsersAPITest extends UsosAPIBaseTest {

    @Disabled
    @Test
    void user2() {
        log(userApi.users().user2());
    }

    @Test
    void employmentGroup() {
        log(userApi.users().employmentGroup("N1"));
    }

    @Test
    void photo() {
        log(userApi.users().photo());
    }

    @Test
    void employmentFunctions() {
        log(userApi.users().employmentFunctions());
        log(userApi.users().employmentFunctions("570"));
    }

    @Test
    void searchCurrentTeachers() {
        log(userApi.users().searchCurrentTeachers());
        log(userApi.users().searchCurrentTeachers(20));
        log(userApi.users().searchCurrentTeachers("pl"));
        log(userApi.users().searchCurrentTeachers("pl", 10));
    }

    @Test
    void employmentPositions() {
        log(userApi.users().employmentPositions("570"));
    }

    @Test
    void search2() {
        log(userApi.users().search2("pl"));
        log(userApi.users().search2("en"));
        log(userApi.users().search2("pl", "pl"));
        log(userApi.users().search2("en", "pl"));
        log(userApi.users().search2("pl", 40));
        log(userApi.users().search2("en", 40));
        log(userApi.users().search2("pl", "pl", 10));
        log(userApi.users().search2("en", "pl", 10));
    }

    @Test
    void studentProgrammes() {
        log(userApi.users().studentProgrammes());
    }

    @Test
    void users() {
        var result = userApi.users().users(List.of("297126", "40931", "300493", "2137"));
        log(result);
        log(result.values().stream().findFirst().get().firstName());
    }

    @Test
    void searchHistoryAffect() {
        log(userApi.users().searchHistoryAffect("297126"));
    }

    @Test
    void searchStudents() {
        log(userApi.users().searchStudents());
        log(userApi.users().searchStudents(20));
        log(userApi.users().searchStudents("266869"));
        log(userApi.users().searchStudents("Marcel", 20));
    }

    @Test
    void user() {
        log(userApi.users().user());
        log(userApi.users().user("297126"));
    }

    @Test
    void position() {
        log(userApi.users().position("1"));
    }

    @Test
    void searchCurrentStudents() {
        log(userApi.users().searchCurrentStudents());
        log(userApi.users().searchCurrentStudents(20));
        log(userApi.users().searchCurrentStudents("pl"));
        log(userApi.users().searchCurrentStudents("pl", 20));
    }

    @Test
    void staffIndex() {
        log(userApi.users().staffIndex(List.of("W4N")));
        log(userApi.users().staffIndex(List.of("W4N"), 20));
    }

    @Test
    void studentIndex() {
        log(userApi.users().studentIndex(List.of("W04-ISTP-000P-OSIW7")));
        log(userApi.users().studentIndex(List.of("W04-ISTP-000P-OSIW7"), 20));
    }

    @Test
    void employmentGroupsIndex() {
        log(userApi.users().employmentGroupsIndex());
    }

    @Test
    void search() {
        log(userApi.users().search());
        log(userApi.users().search(20));
        log(userApi.users().search("pl"));
        log(userApi.users().search("pl", 20));
    }

    @Test
    void searchStaff() {
        log(userApi.users().searchStaff());
        log(userApi.users().searchStaff(20));
        log(userApi.users().searchStaff("pl"));
        log(userApi.users().searchStaff("pl", 20));
    }
}
