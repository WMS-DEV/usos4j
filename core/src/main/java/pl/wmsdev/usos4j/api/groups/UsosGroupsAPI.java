package pl.wmsdev.usos4j.api.groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.groups.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosGroupsAPI extends UsosUserAPIDefinition implements UsosGroupsServerAPI {

    public UsosGroupsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Beta
    public List<UsosCommonGroup> commonGroups(UsosCommonGroupParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/groups/common_groups", params, FieldSelector.fromRequest(UsosCommonGroup.class)
        ), UsosCommonGroup[].class));
    }

    public Boolean isLecturer(UsosGroupsIsLecturerWithTokenParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/is_lecturer", params
        ), Boolean.class);
    }

    public Boolean isParticipant(UsosGroupsIsParticipantParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/is_participant", params
        ), Boolean.class);
    }

    public UsosGroupsParticipant participant(UsosGroupsParticipantParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/participant", params, FieldSelector.fromRequest(UsosGroup.class)
        ), UsosGroupsParticipant.class);
    }

    public UsosGroupsLecturer lecturer() {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/lecturer", FieldSelector.fromRequest(UsosGroup.class)
        ), UsosGroupsLecturer.class);
    }

    public UsosClassGroup classGroup(UsosClassGroupParamsWithToken params) {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/class_group", params, FieldSelector.fromRequest(UsosClassGroup.class)
        ), UsosClassGroup.class);
    }

    @Override
    public UsosClassGroup classGroup(UsosClassGroupParams params) {
        return request(requestFactory.get(
                "services/groups/class_group", params, FieldSelector.fromRequest(UsosClassGroup.class).removeFields("lecturers", "participants")
        ), UsosClassGroup.class);
    }

    @Override
    public UsosGroup group(UsosGroupParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/group", params, FieldSelector.fromRequest(UsosGroup.class)
        ), UsosGroup.class);
    }

    @Override
    public Map<String, UsosGroup> groups(UsosGroupsParams params) {
        return request(requestFactory.get(
                "services/groups/groups", params, FieldSelector.fromRequest(UsosGroup.class)
        ), new TypeReference<>() {});
    }

    @Override
    public Boolean isLecturer(UsosGroupsIsLecturerParams params) {
        return request(requestFactory.get(
                "services/groups/is_lecturer", params
        ), Boolean.class);
    }

    @Override
    public UsosGroupsLecturer lecturer(UsosGroupsLecturerParams params) {
        return request(requestFactory.get(
                "services/groups/lecturer", params, FieldSelector.fromRequest(UsosGroup.class)
        ), UsosGroupsLecturer.class);
    }

    @Override
    public UsosGroupsUser user(UsosGroupsUserParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/groups/user", params, FieldSelector.fromRequest(UsosGroup.class)
        ), UsosGroupsUser.class);
    }
}