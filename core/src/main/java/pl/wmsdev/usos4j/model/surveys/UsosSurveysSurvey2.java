package pl.wmsdev.usos4j.model.surveys;

import com.fasterxml.jackson.annotation.JsonAlias;
import pl.wmsdev.usos4j.model.common.UsosLocalizedString;
import pl.wmsdev.usos4j.model.common.UsosObject;
import pl.wmsdev.usos4j.model.fac.UsosFaculty;
import pl.wmsdev.usos4j.model.groups.UsosGroup;
import pl.wmsdev.usos4j.model.progs.UsosProgramme;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.List;


public record UsosSurveysSurvey2(
        String id,
        SurveyType surveyType,
        UsosLocalizedString name,
        String headlineHtml,
        String startDate,
        String endDate,
        @JsonAlias("can_i_fill_out") Boolean canIFillOut,
        @JsonAlias("did_i_fill_out") Boolean didIFillOut,
        UsosGroup group,
        List<UsosGroup> groupsConducted,
        UsosUser lecturer,
        UsosFaculty faculty,
        UsosProgramme programme,
        Integer filledOutCount,
        Integer entitledCount,
        @JsonAlias("can_i_view_results") Boolean canIViewResults,
        @JsonAlias("why_cant_i_view_results") UsosLocalizedString whyCantIViewResults,
        List<UsosSurveysQuestion> questions,
        Boolean hasFinalComment
) implements UsosObject {
}
