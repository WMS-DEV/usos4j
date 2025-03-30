package pl.wmsdev.usos4j.model.surveys;

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
        Boolean canIFillOut,
        Boolean didIFillOut,
        UsosGroup group,
        List<UsosGroup> groupsConducted,
        UsosUser lecturer,
        UsosFaculty faculty,
        UsosProgramme programme,
        Integer filledOutCount,
        Integer entitledCount,
        Boolean canIViewResults,
        UsosLocalizedString whyCantIViewResults,
        List<UsosSurveysQuestion> questions,
        Boolean hasFinalComment
) implements UsosObject {
}
