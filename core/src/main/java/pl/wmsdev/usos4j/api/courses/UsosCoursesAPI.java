package pl.wmsdev.usos4j.api.courses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.courses.*;

import java.util.Map;

public class UsosCoursesAPI extends UsosUserAPIDefinition implements UsosCoursesServerAPI {

    public UsosCoursesAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Override
    public Map<String, UsosClassType> classTypesIndex() {
        return request(requestFactory.get("services/courses/classtypes_index"),
                new TypeReference<>() {});
    }

    @Override
    public UsosCourse course(UsosCourseParam params) {
        return request(requestFactory.get("services/courses/course",
                        params, FieldSelector.fromRequest(UsosCourse.class)),
                UsosCourse.class);
    }

    @Override
    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public UsosCourse2 course2(UsosCourseParam params) {
        return request(requestFactory.get("services/courses/course2",
                        params, FieldSelector.fromRequest(UsosCourse2.class)),
                UsosCourse2.class);
    }

    public UsosCourseEdition courseEdition(UsosCourseEditionParam params) {
        return requestWithAccessToken(requestFactory.get("services/courses/course_edition",
                        params, FieldSelector.fromRequest(UsosCourseEdition.class)),
                UsosCourseEdition.class);
    }

    public UsosCourseEdition2 courseEdition2(UsosCourseEditionParam params) {
        return requestWithAccessToken(requestFactory.get("services/courses/course_edition2",
                        params, FieldSelector.fromRequest(UsosCourseEdition2.class)),
                UsosCourseEdition2.class);
    }

    public UsosCourseUnit courseUnit(UsosCourseUnitParam params) {
        return requestWithAccessToken(requestFactory.get("services/courses/course_unit",
                        params, FieldSelector.fromRequest(UsosCourseUnit.class)),
                UsosCourseUnit.class);
    }

    @Override
    public Map<String, UsosCourse> courses(UsosCoursesParam params) {
        return request(requestFactory.get("services/courses/courses",
                        params, FieldSelector.fromRequest(UsosCourse.class)),
                new TypeReference<>() {});
    }

    public boolean isParticipant(UsosCourseIsParticipantParam params) {
        return requestWithAccessToken(requestFactory.get("services/courses/is_participant",
                params), Boolean.class);
    }

    @Override
    public UsosCourseSearch search(UsosCourseSearchParam params) {
        return request(requestFactory.get("services/courses/search",
                        params, FieldSelector.fromRequest(UsosCourse.class)),
                UsosCourseSearch.class);
    }

    public UsosUnit unit(UsosUnitParam params) {
        return requestWithAccessToken(requestFactory.get("services/courses/unit",
                        params, FieldSelector.fromRequest(UsosUnit.class)),
                UsosUnit.class);
    }

    public Map<String, UsosUnit> units(UsosUnitsParam params) {
        return requestWithAccessToken(requestFactory.get("services/courses/units",
                        params, FieldSelector.fromRequest(UsosUnit.class)),
                new TypeReference<>() {});
    }

    @Beta
    public UsosCoursesUser user() {
        return requestWithAccessToken(requestFactory.get("services/courses/user",
                        UsosCourseUserParam.builder().build(), FieldSelector.fromRequest(UsosCoursesUser.class)),
                UsosCoursesUser.class);
    }

    public Map<String, Map<String, String>> userEctsPoints() {
        return requestWithAccessToken(requestFactory.get("services/courses/user_ects_points"),
                new TypeReference<>() {});
    }

}
