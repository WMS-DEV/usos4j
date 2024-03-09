package pl.wmsdev.usos4j.api.registrations;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.registrations.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosRegistrationsAPI extends UsosUserAPIDefinition {

    public UsosRegistrationsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public List<UsosRegistrationRound> courseRegistrationRounds(UsosCourseRegistrationRoundsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/course_registration_rounds", params, FieldSelector.fromRequest(UsosRegistrationRound.class)
        ), UsosRegistrationRound[].class));
    }

    public List<UsosCoursesCart> coursesCart() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/courses_cart", FieldSelector.fromRequest(UsosCoursesCart.class)
        ), UsosCoursesCart[].class));
    }

    public List<UsosRegistration> facultyRegistrations(UsosFacultyRegistrationsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/faculty_registrations", params, FieldSelector.fromRequest(UsosRegistration.class)
        ), UsosRegistration[].class));
    }

    public List<UsosTokenRegistration> facultyTokenRegistrations(UsosFacultyTokenRegistrationsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/faculty_token_registrations", params, FieldSelector.fromRequest(UsosTokenRegistration.class)
        ), UsosTokenRegistration[].class));
    }

    public UsosRanking ranking(UsosRankingParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/ranking", params, FieldSelector.fromRequest(UsosRanking.class)
        ), UsosRanking.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public Map<?, ?> register(UsosRegisterParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/register", params
        ), Map.class);
    }

    public UsosRegistration registration(UsosRegistrationParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/registration", params, FieldSelector.fromRequest(UsosRegistration.class)
        ), UsosRegistration.class);
    }

    public UsosRegistrationCourse registrationCourse(UsosRegistrationCourseParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/registration_course", params, FieldSelector.fromRequest(UsosRegistrationCourse.class)
        ), UsosRegistrationCourse.class);
    }

    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public UsosRegistrationRound registrationRound(UsosRegistrationRoundParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/registration_round", params, FieldSelector.fromRequest(UsosRegistrationRound.class)
        ), UsosRegistrationRound.class);
    }

    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public List<UsosRegistrationRoundCourse> registrationRoundCourses(UsosRegistrationRoundCoursesParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/registration_round_courses", params, FieldSelector.fromRequest(UsosRegistrationRoundCourse.class)
        ), UsosRegistrationRoundCourse[].class));
    }

    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public List<UsosRegistrationRound> searchRounds(UsosSearchRoundsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/search_rounds", params, FieldSelector.fromRequest(UsosRegistrationRound.class)
        ), UsosRegistrationRound[].class));
    }

    public List<UsosTokenRegistrationRound> searchTokenRounds(UsosSearchTokenRoundsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/search_token_rounds", params, FieldSelector.fromRequest(UsosTokenRegistrationRound.class)
        ), UsosTokenRegistrationRound[].class));
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public UsosTokenRegistration tokenRegistration(UsosTokenRegistrationParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/token_registration", params, FieldSelector.fromRequest(UsosTokenRegistration.class)
        ), UsosTokenRegistration.class);
    }

    public UsosTokenRegistrationCourse tokenRegistrationCourse(UsosTokenRegistrationCourseParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/token_registration_course", params, FieldSelector.fromRequest(UsosTokenRegistrationCourse.class)
        ), UsosTokenRegistrationCourse.class);
    }

    public UsosTokenRegistrationRound tokenRegistrationRound(UsosTokenRegistrationRoundParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/token_registration_round", params, FieldSelector.fromRequest(UsosTokenRegistrationRound.class)
        ), UsosTokenRegistrationRound.class);
    }

    @NotTested(reason = NotTestedReason.NO_DATA)
    public Map<?, ?> unregister(UsosUnregisterParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/registrations/unregister", params
        ), Map.class);
    }

    public List<UsosRegistration> userRegistrations(UsosUserRegistrationsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/user_registrations", params, FieldSelector.fromRequest(UsosRegistration.class)
        ), UsosRegistration[].class));
    }

    public List<UsosTokenRegistration> userTokenRegistrations(UsosUserTokenRegistrationsParams params) {
        return Arrays.asList(requestWithAccessToken(requestFactory.get(
                "services/registrations/user_token_registrations", params, FieldSelector.fromRequest(UsosTokenRegistration.class)
        ), UsosTokenRegistration[].class));
    }
}

