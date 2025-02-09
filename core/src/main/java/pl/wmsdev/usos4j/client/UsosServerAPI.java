package pl.wmsdev.usos4j.client;

import lombok.RequiredArgsConstructor;
import pl.wmsdev.usos4j.api.apiref.UsosApiRefAPI;
import pl.wmsdev.usos4j.api.apisrv.UsosApiSrvAPI;
import pl.wmsdev.usos4j.api.courses.UsosCoursesServerAPI;
import pl.wmsdev.usos4j.api.fac.UsosFacultiesServerAPI;
import pl.wmsdev.usos4j.api.feedback.UsosFeedbackServerAPI;
import pl.wmsdev.usos4j.api.generic.UsosGenericServerAPI;
import pl.wmsdev.usos4j.api.geo.UsosGeoServerAPI;
import pl.wmsdev.usos4j.api.grades.UsosGradesServerAPI;
import pl.wmsdev.usos4j.api.groups.UsosGroupsServerAPI;
import pl.wmsdev.usos4j.api.housing.UsosHousingServerAPI;
import pl.wmsdev.usos4j.api.mailing.UsosMailingServerAPI;
import pl.wmsdev.usos4j.api.phones.UsosPhonesServerAPI;
import pl.wmsdev.usos4j.api.plctests.UsosPlacementTestsServerAPI;
import pl.wmsdev.usos4j.api.progs.UsosProgsServerAPI;
import pl.wmsdev.usos4j.api.terms.UsosTermsAPI;
import pl.wmsdev.usos4j.api.timetable.UsosTimeTableServerAPI;

@RequiredArgsConstructor
public class UsosServerAPI {

    private final UsosApiSrvAPI apiSrvApi;
    private final UsosApiRefAPI apiRefApi;
    private final UsosPhonesServerAPI phonesServerAPI;
    private final UsosTimeTableServerAPI timeTableServerAPI;
    private final UsosFeedbackServerAPI feedbackServerApi;
    private final UsosMailingServerAPI mailingServerApi;
    private final UsosGenericServerAPI genericUserApi;
    private final UsosTermsAPI termsServerApi;
    private final UsosFacultiesServerAPI facultiesServerAPI;
    private final UsosGroupsServerAPI groupsServerAPI;
    private final UsosPlacementTestsServerAPI usosPlacementTestsServerAPI;
    private final UsosGradesServerAPI usosGradesServerAPI;
    private final UsosCoursesServerAPI usosCoursesServerAPI;
    private final UsosProgsServerAPI usosProgsServerAPI;
    private final UsosGeoServerAPI usosGeoServerAPI;
    private final UsosHousingServerAPI usosHousingServerAPI;

    public UsosApiSrvAPI apiSrv() {
        return apiSrvApi;
    }

    public UsosApiRefAPI apiRef() {
        return apiRefApi;
    }

    public UsosPhonesServerAPI phones() {
        return phonesServerAPI;
    }

    public UsosTimeTableServerAPI tt() {
        return timeTableServerAPI;
    }

    public UsosTimeTableServerAPI timeTable() {
        return tt();
    }

    public UsosMailingServerAPI mailing() {
        return mailingServerApi;
    }

    public UsosFeedbackServerAPI feedback() {
        return feedbackServerApi;
    }

    public UsosGenericServerAPI generic() {
        return genericUserApi;
    }

    public UsosFacultiesServerAPI fac() {
        return facultiesServerAPI;
    }

    public UsosTermsAPI terms() {
        return termsServerApi;
    }

    public UsosGroupsServerAPI groups() {
        return groupsServerAPI;
    }

    public UsosPlacementTestsServerAPI plcTests() {
        return usosPlacementTestsServerAPI;
    }

    public UsosGradesServerAPI grades() {
        return usosGradesServerAPI;
    }

    public UsosCoursesServerAPI courses() {
        return usosCoursesServerAPI;
    }

    public UsosProgsServerAPI progs() {
        return usosProgsServerAPI;
    }

    public UsosGeoServerAPI geo() {
        return usosGeoServerAPI;
    }

    public UsosHousingServerAPI housing() {
        return usosHousingServerAPI;
    }
}
