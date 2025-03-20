package pl.wmsdev.usos4j.client;

import lombok.RequiredArgsConstructor;
import pl.wmsdev.usos4j.api.calendar.UsosCalendarAPI;
import pl.wmsdev.usos4j.api.cards.UsosCardsAPI;
import pl.wmsdev.usos4j.api.courses.UsosCoursesAPI;
import pl.wmsdev.usos4j.api.credits.UsosCreditsAPI;
import pl.wmsdev.usos4j.api.feedback.UsosFeedbackAPI;
import pl.wmsdev.usos4j.api.generic.UsosGenericUserAPI;
import pl.wmsdev.usos4j.api.geo.UsosGeoAPI;
import pl.wmsdev.usos4j.api.grades.UsosGradesAPI;
import pl.wmsdev.usos4j.api.groups.UsosGroupsAPI;
import pl.wmsdev.usos4j.api.housing.UsosHousingAPI;
import pl.wmsdev.usos4j.api.mailing.UsosMailingAPI;
import pl.wmsdev.usos4j.api.news.UsosNewsAPI;
import pl.wmsdev.usos4j.api.payments.UsosPaymentsAPI;
import pl.wmsdev.usos4j.api.phones.UsosPhonesAPI;
import pl.wmsdev.usos4j.api.photos.UsosPhotosAPI;
import pl.wmsdev.usos4j.api.plctests.UsosPlacementTestsAPI;
import pl.wmsdev.usos4j.api.progs.UsosProgsAPI;
import pl.wmsdev.usos4j.api.registrations.UsosRegistrationsAPI;
import pl.wmsdev.usos4j.api.surveys.UsosSurveysAPI;
import pl.wmsdev.usos4j.api.theses.UsosThesesAPI;
import pl.wmsdev.usos4j.api.timetable.UsosTimeTableAPI;

@RequiredArgsConstructor
public class UsosUserAPI {
    private final UsosUsersAPI userApi;
    private final UsosCoursesAPI coursesApi;
    private final UsosCalendarAPI calendarApi;
    private final UsosCardsAPI cardsApi;
    private final UsosPhonesAPI phonesAPI;
    private final UsosTimeTableAPI timeTableActivity;
    private final UsosPhotosAPI usosPhotosApi;
    private final UsosNewsAPI usosNewsAPI;
    private final UsosCreditsAPI usosCreditsApi;
    private final UsosFeedbackAPI usosFeedbackApi;
    private final UsosMailingAPI usosMailingApi;
    private final UsosThesesAPI usosThesesApi;
    private final UsosGenericUserAPI genericUserApi;
    private final UsosGroupsAPI groupsAPI;
    private final UsosPaymentsAPI usosPaymentsApi;
    private final UsosPlacementTestsAPI usosPlacementTestsAPI;
    private final UsosRegistrationsAPI usosRegistrationsApi;
    private final UsosGradesAPI usosGradesAPI;
    private final UsosProgsAPI usosProgsAPI;
    private final UsosGeoAPI usosGeoAPI;
    private final UsosHousingAPI usosHousingAPI;
    private final UsosSurveysAPI usosSurveysAPI;

    public UsosUsersAPI users() {
        return userApi;
    }

    public UsosCoursesAPI courses() {
        return coursesApi;
    }

    public UsosCalendarAPI calendar() {
        return calendarApi;
    }

    public UsosCardsAPI cards() {
        return cardsApi;
    }

    public UsosPhonesAPI phones() {
        return phonesAPI;
    }

    public UsosTimeTableAPI tt() {
        return timeTableActivity;
    }

    public UsosTimeTableAPI timeTable() {
        return tt();
    }

    public UsosPhotosAPI photos() {
        return usosPhotosApi;
    }

    public UsosNewsAPI news() {
        return usosNewsAPI;
    }

    public UsosCreditsAPI credits() {
        return usosCreditsApi;
    }

    public UsosFeedbackAPI feedback() {
        return usosFeedbackApi;
    }

    public UsosMailingAPI mailing() {
        return usosMailingApi;
    }

    public UsosThesesAPI theses() {
        return usosThesesApi;
    }

    public UsosGenericUserAPI generic() {
        return genericUserApi;
    }

    public UsosGroupsAPI groups() {
        return groupsAPI;
    }

    public UsosPaymentsAPI payments() {
        return usosPaymentsApi;
    }

    public UsosPlacementTestsAPI plcTests() {
        return usosPlacementTestsAPI;
    }

    public UsosRegistrationsAPI registrations() {
        return usosRegistrationsApi;
    }

    public UsosGradesAPI grades() {
        return usosGradesAPI;
    }

    public UsosProgsAPI progs() {
        return usosProgsAPI;
    }

    public UsosGeoAPI geo() {
        return usosGeoAPI;
    }


    public UsosHousingAPI housing() {
        return usosHousingAPI;
    }

    public UsosSurveysAPI surveys() {
        return usosSurveysAPI;
    }
}
