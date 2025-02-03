package pl.wmsdev.usos4j.client;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.api.apiref.UsosApiRefAPI;
import pl.wmsdev.usos4j.api.apisrv.UsosApiSrvAPI;
import pl.wmsdev.usos4j.api.calendar.UsosCalendarAPI;
import pl.wmsdev.usos4j.api.cards.UsosCardsAPI;
import pl.wmsdev.usos4j.api.courses.UsosCoursesAPI;
import pl.wmsdev.usos4j.api.credits.UsosCreditsAPI;
import pl.wmsdev.usos4j.api.fac.UsosFacultiesServerAPI;
import pl.wmsdev.usos4j.api.feedback.UsosFeedbackAPI;
import pl.wmsdev.usos4j.api.geo.UsosGeoAPI;
import pl.wmsdev.usos4j.api.grades.UsosGradesAPI;
import pl.wmsdev.usos4j.api.groups.UsosGroupsAPI;
import pl.wmsdev.usos4j.api.housing.UsosHousingAPI;
import pl.wmsdev.usos4j.api.mailing.UsosMailingAPI;
import pl.wmsdev.usos4j.api.mailing.UsosMailingServerAPI;
import pl.wmsdev.usos4j.api.generic.UsosGenericServerAPI;
import pl.wmsdev.usos4j.api.generic.UsosGenericUserAPI;
import pl.wmsdev.usos4j.api.news.UsosNewsAPI;
import pl.wmsdev.usos4j.api.payments.UsosPaymentsAPI;
import pl.wmsdev.usos4j.api.phones.UsosPhonesAPI;
import pl.wmsdev.usos4j.api.photos.UsosPhotosAPI;
import pl.wmsdev.usos4j.api.plctests.UsosPlacementTestsAPI;
import pl.wmsdev.usos4j.api.progs.UsosProgsAPI;
import pl.wmsdev.usos4j.api.registrations.UsosRegistrationsAPI;
import pl.wmsdev.usos4j.api.terms.UsosTermsAPI;
import pl.wmsdev.usos4j.api.theses.UsosThesesAPI;
import pl.wmsdev.usos4j.api.timetable.UsosTimeTableAPI;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.auth.UsosRequestToken;
import pl.wmsdev.usos4j.model.auth.UsosScope;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.Set;

public class Usos {

    @Getter
    private final OAuth10aService oAuthService;
    private final String baseUrl;

    @Builder
    public Usos(String baseUrl, String callbackUrl, String consumerKey, String consumerSecret, boolean debug, @Singular Set<UsosScope> scopes) {
        var serviceBuilder = new ServiceBuilder(consumerKey)
                .apiSecret(consumerSecret);
        if (callbackUrlGiven(callbackUrl)) {
            serviceBuilder.callback(callbackUrl);
        }

        if (debug) serviceBuilder.debug();
        this.oAuthService = serviceBuilder.build(new ScribeBasedUsosAPI(baseUrl, scopes));
        this.baseUrl = baseUrl;
    }

    private boolean callbackUrlGiven(String callbackUrl) {
        return callbackUrl != null && !callbackUrl.isEmpty();
    }

    public static Usos serverOnly(String baseUrl, String consumerKey, String consumerSecret) {
        return new Usos(baseUrl, "", consumerKey, consumerSecret,false, Collections.emptySet());
    }

    @SneakyThrows
    public UsosRequestToken getRequestToken() {
        return UsosRequestToken.from(oAuthService.getRequestToken());
    }

    @SneakyThrows
    public UsosAccessToken getAccessToken(UsosRequestToken requestToken, String verifier) {
        return UsosAccessToken.from(oAuthService.getAccessToken(requestToken.toFrameworkToken(), verifier));
    }

    public String getAuthorizationUrl(UsosRequestToken requestToken) {
        return oAuthService.getAuthorizationUrl(requestToken.toFrameworkToken());
    }

    public UsosUserAPI getUserApi(UsosAccessToken accessToken) {
        var requestFactory = new ScribeOAuthRequestFactory(baseUrl);
        return new UsosUserAPI(new UsosUsersAPI(oAuthService, requestFactory, accessToken),
                new UsosCoursesAPI(oAuthService, requestFactory, accessToken),
                new UsosCalendarAPI(oAuthService, requestFactory, accessToken),
                new UsosCardsAPI(oAuthService, requestFactory, accessToken),
                new UsosPhonesAPI(oAuthService, requestFactory, accessToken),
                new UsosTimeTableAPI(oAuthService, requestFactory, accessToken),
                new UsosPhotosAPI(oAuthService, requestFactory, accessToken),
                new UsosNewsAPI(oAuthService, requestFactory, accessToken),
                new UsosCreditsAPI(oAuthService, requestFactory, accessToken),
                new UsosFeedbackAPI(oAuthService, requestFactory, accessToken),
                new UsosMailingAPI(oAuthService, requestFactory, accessToken),
                new UsosThesesAPI(oAuthService, requestFactory, accessToken),
                new UsosGenericUserAPI(oAuthService, requestFactory, accessToken),
                new UsosGroupsAPI(oAuthService, requestFactory, accessToken),
                new UsosPaymentsAPI(oAuthService, requestFactory, accessToken),
                new UsosPlacementTestsAPI(oAuthService, requestFactory, accessToken),
                new UsosRegistrationsAPI(oAuthService, requestFactory, accessToken),
                new UsosGradesAPI(oAuthService, requestFactory, accessToken),
                new UsosProgsAPI(oAuthService, requestFactory, accessToken),
                new UsosGeoAPI(oAuthService, requestFactory, accessToken),
                new UsosHousingAPI(oAuthService, requestFactory, accessToken));
    }

    public UsosServerAPI getServerApi() {
        var requestFactory = new ScribeOAuthRequestFactory(baseUrl);
        return new UsosServerAPI(new UsosApiSrvAPI(oAuthService, requestFactory),
                new UsosApiRefAPI(oAuthService, requestFactory),
                new UsosPhonesAPI(oAuthService, requestFactory, null),
                new UsosTimeTableAPI(oAuthService, requestFactory, null),
                new UsosFeedbackAPI(oAuthService, requestFactory, null),
                new UsosMailingServerAPI(oAuthService, requestFactory),
                new UsosGenericServerAPI(oAuthService, requestFactory),
                new UsosTermsAPI(oAuthService, requestFactory),
                new UsosFacultiesServerAPI(oAuthService, requestFactory),
                new UsosGroupsAPI(oAuthService, requestFactory, null),
                new UsosPlacementTestsAPI(oAuthService, requestFactory, null),
                new UsosGradesAPI(oAuthService, requestFactory, null),
                new UsosCoursesAPI(oAuthService, requestFactory, null),
                new UsosProgsAPI(oAuthService, requestFactory, null),
                new UsosGeoAPI(oAuthService, requestFactory, null),
                new UsosHousingAPI(oAuthService, requestFactory, null));
    }

}
