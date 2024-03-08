package dev.wms.usos4j.client;

import dev.wms.usos4j.api.calendar.UsosCalendarAPI;
import dev.wms.usos4j.api.cards.UsosCardsAPI;
import dev.wms.usos4j.api.courses.UsosCoursesAPI;
import dev.wms.usos4j.api.credits.UsosCreditsAPI;
import dev.wms.usos4j.api.feedback.UsosFeedbackAPI;
import dev.wms.usos4j.api.groups.UsosGroupsAPI;
import dev.wms.usos4j.api.mailing.UsosMailingAPI;
import dev.wms.usos4j.api.generic.UsosGenericUserAPI;
import dev.wms.usos4j.api.news.UsosNewsAPI;
import dev.wms.usos4j.api.payments.UsosPaymentsAPI;
import dev.wms.usos4j.api.phones.UsosPhonesAPI;
import dev.wms.usos4j.api.photos.UsosPhotosAPI;
import dev.wms.usos4j.api.plctests.UsosPlacementTestsAPI;
import dev.wms.usos4j.api.registrations.UsosRegistrationsAPI;
import dev.wms.usos4j.api.theses.UsosThesesAPI;
import dev.wms.usos4j.api.timetable.UsosTimeTableAPI;
import lombok.RequiredArgsConstructor;

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

    public UsosPlacementTestsAPI plcTests() { return usosPlacementTestsAPI;}
  
    public UsosRegistrationsAPI registrations() {
      return usosRegistrationsApi;
    }
}
