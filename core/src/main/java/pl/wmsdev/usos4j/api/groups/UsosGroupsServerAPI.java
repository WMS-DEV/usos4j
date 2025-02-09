package pl.wmsdev.usos4j.api.groups;

import pl.wmsdev.usos4j.model.groups.*;

import java.util.Map;

public interface UsosGroupsServerAPI {

    UsosClassGroup classGroup(UsosClassGroupParams params);

    UsosGroup group(UsosGroupParams params);

    Map<String, UsosGroup> groups(UsosGroupsParams params);

    Boolean isLecturer(UsosGroupsIsLecturerParams params);

    UsosGroupsLecturer lecturer(UsosGroupsLecturerParams params);

    UsosGroupsUser user(UsosGroupsUserParams params);

}
