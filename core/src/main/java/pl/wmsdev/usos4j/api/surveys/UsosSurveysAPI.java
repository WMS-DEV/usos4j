package pl.wmsdev.usos4j.api.surveys;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.docs.NotTested;
import pl.wmsdev.usos4j.docs.NotTestedReason;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.surveys.*;

import java.util.List;
import java.util.Map;

public class UsosSurveysAPI extends UsosUserAPIDefinition {

    public UsosSurveysAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    /**
     * @return An empty {@code Map} representing success.
     */
    @Beta
    @NotTested(reason = NotTestedReason.NO_ACCESS)
    public Map fillOut2(UsosSurveysFillOut2Params params) {
        return requestWithAccessToken(requestFactory.get(
                "services/surveys/fill_out2", params
                ), new TypeReference<>() {});
    }

    @Beta
    public UsosSurveysSurvey2 survey2(UsosSurveysSurvey2Params params) {
        return requestWithAccessToken(requestFactory.get(
                "services/surveys/survey2", params,
                FieldSelector.fromRequest(UsosSurveysSurvey2.class)
        ), UsosSurveysSurvey2.class);
    }

    @Beta
    public Map<String, UsosSurveysSurvey2> surveys2(UsosSurveysSurveys2Params params) {
        return requestWithAccessToken(requestFactory.get(
                "services/surveys/surveys2", params,
                FieldSelector.fromRequest(UsosSurveysSurvey2.class)
        ), new TypeReference<>() {});
    }

    @Beta
    public List<UsosSurveysSurvey2> surveysToFill2(UsosSurveysSurveysToFill2Params params) {
        return requestWithAccessToken(requestFactory.get(
                "services/surveys/surveys_to_fill2", params,
                FieldSelector.fromRequest(UsosSurveysSurvey2.class)
        ), new TypeReference<>() {});
    }
}
