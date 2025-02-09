package pl.wmsdev.usos4j.api.timetable;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.timetable.UsosTimeTableActivity;
import pl.wmsdev.usos4j.model.timetable.UsosTimeTableWebcalUrl;
import pl.wmsdev.usos4j.utils.CollectionUtils;
import pl.wmsdev.usos4j.utils.UsosDateUtils;

import java.time.LocalDate;
import java.util.*;

public class UsosTimeTableAPI extends UsosUserAPIDefinition implements UsosTimeTableServerAPI {

    public UsosTimeTableAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Override
    public List<UsosTimeTableActivity> staff(Integer userId) {
        return Arrays.asList(request(requestFactory.get("services/tt/staff", getTimeTableParams(userId, null, null)),
                UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> staff(Integer userId, Integer days) {
        return Arrays.asList(request(requestFactory.get("services/tt/staff", getTimeTableParams(userId, null, days)),
                UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> staff(Integer userId, LocalDate start, Integer days) {
        return Arrays.asList(request(requestFactory.get("services/tt/staff",
                        getTimeTableParams(userId, start, days)),
                UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> student() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/student",
                getTimeTableFields()), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> student(LocalDate start) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/student",
                getTimeTableParams(start, null)), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> student(int days) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/student",
                getTimeTableParams(null, days)), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> student(LocalDate start, int days) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/student",
                getTimeTableParams(start, days)), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> user(LocalDate start, int days) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/user",
                getTimeTableParams(start, days)), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> user(LocalDate start) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/user",
                getTimeTableParams(start, null)), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> user(int days) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/user",
                getTimeTableParams(null, days)), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> user() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/user",
                getTimeTableFields()), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEdition(String courseId, String termId, LocalDate start, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_edition",
                CollectionUtils.mergeMaps(Map.of("course_id", List.of(courseId), "term_id", List.of(termId)),
                        getTimeTableParams(start, days))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEdition(String courseId, String termId, LocalDate start) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_edition",
                CollectionUtils.mergeMaps(Map.of("course_id", List.of(courseId), "term_id", List.of(termId)),
                        getTimeTableParams(start, null))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEdition(String courseId, String termId, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_edition",
                CollectionUtils.mergeMaps(Map.of("course_id", List.of(courseId), "term_id", List.of(termId)),
                        getTimeTableParams(null, days))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEdition(String courseId, String termId) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_edition",
                CollectionUtils.mergeMaps(Map.of("course_id", List.of(courseId), "term_id", List.of(termId)),
                        getTimeTableFields())), UsosTimeTableActivity[].class));
    }


    public UsosTimeTableWebcalUrl upcomingShare(String lang) {
        return requestWithAccessToken(requestFactory.get("services/tt/upcoming_share", Map.of("fields", List.of("webcal_url"),
                "lang", List.of(lang))), UsosTimeTableWebcalUrl.class);
    }

    @Override
    public List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber, LocalDate start, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroup",
                CollectionUtils.mergeMaps(Map.of("group_number", List.of(groupNumber), "unit_id", List.of(unitId)),
                        getTimeTableParams(start, days))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber, LocalDate start) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroup",
                CollectionUtils.mergeMaps(Map.of("group_number", List.of(groupNumber), "unit_id", List.of(unitId)),
                        getTimeTableParams(start, null))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroup",
                CollectionUtils.mergeMaps(Map.of("group_number", List.of(groupNumber), "unit_id", List.of(unitId)),
                        getTimeTableParams(null, days))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroup(String unitId, String groupNumber) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroup",
                CollectionUtils.mergeMaps(Map.of("group_number", List.of(groupNumber), "unit_id", List.of(unitId)),
                        getTimeTableFields())), UsosTimeTableActivity[].class));
    }

    @Override
    public List<LocalDate> classgroupDates(String unitId, String groupNumber) {
        return Arrays.stream(request(requestFactory.get("services/tt/classgroup_dates",
                        CollectionUtils.mergeMaps(Map.of("group_number", List.of(groupNumber), "unit_id", List.of(unitId)),
                                getTimeTableFields())), String[].class))
                .map(UsosDateUtils::toDate)
                .toList();
    }

    @Override
    public List<UsosTimeTableActivity> classgroupDatesTwo(String unitId, String groupNumber) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroup_dates2",
                CollectionUtils.mergeMaps(Map.of("group_number", List.of(groupNumber), "unit_id", List.of(unitId)),
                        getTimeTableFields())), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroups(List<String> classgroupIds, boolean partial, LocalDate start, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroups",
                CollectionUtils.mergeMaps(Map.of("classgroup_ids", classgroupIds, "partial", List.of(String.valueOf(partial))),
                        getTimeTableParams(start, days))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroups(List<String> classgroupIds, boolean partial, LocalDate start) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroups",
                CollectionUtils.mergeMaps(Map.of("classgroup_ids", classgroupIds, "partial", List.of(String.valueOf(partial))),
                        getTimeTableParams(start, null))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroups(List<String> classgroupIds, boolean partial, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroups",
                CollectionUtils.mergeMaps(Map.of("classgroup_ids", classgroupIds, "partial", List.of(String.valueOf(partial))),
                        getTimeTableParams(null, days))), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> classgroups(List<String> classgroupIds) {
        return Arrays.asList(request(requestFactory.get("services/tt/classgroups",
                CollectionUtils.mergeMaps(Map.of("classgroup_ids", classgroupIds),
                        getTimeTableFields())), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> room(String roomId, LocalDate start, int days) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/room",
                CollectionUtils.mergeMaps(Map.of("room_id", List.of(roomId)), getTimeTableParams(start, days))), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> room(String roomId, LocalDate start) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/room",
                CollectionUtils.mergeMaps(Map.of("room_id", List.of(roomId)), getTimeTableParams(start, null))), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> room(String roomId, int days) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/room",
                CollectionUtils.mergeMaps(Map.of("room_id", List.of(roomId)), getTimeTableParams(null, days))), UsosTimeTableActivity[].class));
    }

    public List<UsosTimeTableActivity> room(String roomId) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/tt/room",
                CollectionUtils.mergeMaps(Map.of("room_id", List.of(roomId)), getTimeTableFields())), UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEditions(String courseEditionIds, boolean partial, LocalDate start, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_editions",
                        CollectionUtils.mergeMaps(Map.of(
                                        "course_edition_ids", List.of(courseEditionIds),
                                        "partial", List.of(String.valueOf(partial)
                                        )),
                                getTimeTableParams(start, days))),
                UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEditions(String courseEditionIds, boolean partial, LocalDate start) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_editions",
                        CollectionUtils.mergeMaps(Map.of(
                                        "course_edition_ids", List.of(courseEditionIds),
                                        "partial", List.of(String.valueOf(partial)
                                        )),
                                getTimeTableParams(start, null))),
                UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEditions(String courseEditionIds, boolean partial, int days) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_editions",
                        CollectionUtils.mergeMaps(Map.of(
                                        "course_edition_ids", List.of(courseEditionIds),
                                        "partial", List.of(String.valueOf(partial)
                                        )),
                                getTimeTableParams(null, days))),
                UsosTimeTableActivity[].class));
    }

    @Override
    public List<UsosTimeTableActivity> courseEditions(String courseEditionIds) {
        return Arrays.asList(request(requestFactory.get("services/tt/course_editions",
                CollectionUtils.mergeMaps(Map.of("course_edition_ids", List.of(courseEditionIds)),
                        getTimeTableFields())), UsosTimeTableActivity[].class));
    }

    public String upcomingIcal(String userId, String lang) {
        return requestWithAccessToken(requestFactory.get("services/tt/upcoming_ical",
                Map.of("user_id", List.of(userId), "lang", List.of(lang))), String.class);
    }

    private Map<String, Collection<String>> getTimeTableFields() {
        return getTimeTableParams(null, null);
    }

    private Map<String, Collection<String>> getTimeTableParams(LocalDate start, Integer days) {
        return getTimeTableParams(null, start, days);
    }

    private Map<String, Collection<String>> getTimeTableParams(Integer userId, LocalDate start, Integer days) {
        Map<String, Collection<String>> result = new HashMap<>();
        result.put("fields", List.of("type", "start_time",
                "end_time", "name", "url", "course_id", "course_name", "lecturer_ids", "group_number", "classgroup_profile_url",
                "building_name", "building_id", "room_number", "room_id", "unit_id", "classtype_id", "cgwm_id", "frequency"));
        if (userId != null) result.put("user_id", List.of(userId.toString()));
        if (start != null) result.put("start", List.of(UsosDateUtils.formatAsDate(start)));
        if (days != null) result.put("days", List.of(days.toString()));
        return result;
    }

}
