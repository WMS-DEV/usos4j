package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.groups.*;

public class UsosGroupsAPITest extends UsosAPIBaseTest {


    @Test
    void commonGroups() {
        log(userApi.groups().commonGroups(UsosCommonGroupParams.builder("622").build()));
    }

    @Test
    void isLecturer() {
        log(userApi.groups().isLecturer(UsosGroupsIsLecturerParams.builder(53875, 1, "622").build()));
    }

    @Test
    void isLecturerWithToken() {
        log(userApi.groups().isLecturer(UsosGroupsIsLecturerWithTokenParams.builder(53875, 1).build()));
    }

    @Test
    void isParticipant() {
        log(userApi.groups().isParticipant(UsosGroupsIsParticipantParams.builder(53875, 1).build()));
    }

    @Test
    void lecturer() {
        log(userApi.groups().lecturer(UsosGroupsLecturerParams.builder("622").build()));
    }

    @Test
    void lecturerWithToken() {
        log(userApi.groups().lecturer());
    }

    @Test
    void participant() {
        log(userApi.groups().participant(UsosGroupsParticipantParams.builder().build()));
    }

    @Test
    void user() {
        log(userApi.groups().user(UsosGroupsUserParams.builder().build()));
    }

    @Test
    @Disabled("PWR denies access")
    void groups() {
        log(userApi.groups().groups(UsosGroupsParams.helper().addGroup(53875, 1).build()));
    }

    @Test
    void classGroup() {
        log(userApi.groups().classGroup(UsosClassGroupParams.builder(53875, 1).build()));
    }

    @Test
    void classGroupWithToken() {
        log(userApi.groups().classGroup(UsosClassGroupParamsWithToken.builder(53875, 1).build()));
    }

}
