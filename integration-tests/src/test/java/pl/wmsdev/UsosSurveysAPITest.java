package pl.wmsdev;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.surveys.UsosSurveysFillOut2Params;
import pl.wmsdev.usos4j.model.surveys.UsosSurveysSurvey2Params;
import pl.wmsdev.usos4j.model.surveys.UsosSurveysSurveys2Params;
import pl.wmsdev.usos4j.model.surveys.UsosSurveysSurveysToFill2Params;

import java.util.Collections;

public class UsosSurveysAPITest extends UsosAPIBaseTest {

    @Test
    @Disabled("no access")
    void fillOut2() {
        log(userApi.surveys().fillOut2(UsosSurveysFillOut2Params.builder("C|Ank_Std_SJO_24/25Z|72971|135", Collections.emptyMap()).comment("").build()));
    }

    @Test
    void survey2() {
        log(userApi.surveys().survey2(UsosSurveysSurvey2Params.builder("C|Ank_Std_SWFiS_24/25Z|73223|1023").build()));
    }

    @Test
    void surveys2() {
        log(userApi.surveys().surveys2(UsosSurveysSurveys2Params.builder("C|Ank_Std_SWFiS_24/25Z|73223|1023", "C|Ank_Std_SJO_24/25Z|72971|135").build()));
    }

    @Test
    void surveysToFill2() {
        log(userApi.surveys().surveysToFill2(UsosSurveysSurveysToFill2Params.builder().includeFilledOut(true).build()));
    }
}
