package pl.wmsdev.usos4j.api.feedback;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.docs.Beta;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.feedback.UsosFeedbackResponse;
import pl.wmsdev.usos4j.model.feedback.UsosFeedbackStatus;

import java.util.List;
import java.util.Map;

public class UsosFeedbackAPI extends UsosUserAPIDefinition implements UsosFeedbackServerAPI {
    public UsosFeedbackAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Beta
    @Override
    public UsosFeedbackStatus status() {
        return request(requestFactory.get("services/feedback/status"), UsosFeedbackStatus.class);
    }

    @Beta
    public UsosFeedbackResponse reportDb(String userForm, String meta) {
        return requestWithAccessToken(requestFactory.get("services/feedback/report_db", Map.of("meta", List.of(meta),
                "user_form", List.of(userForm))), UsosFeedbackResponse.class);
    }
}