package pl.wmsdev.usos4j.api.credits;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;

import java.util.List;
import java.util.Map;

public class UsosCreditsAPI extends UsosUserAPIDefinition {
    public UsosCreditsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public String usedSum(String studentsProgrammeId) {
        return requestWithAccessToken(requestFactory.get("services/credits/used_sum", Map.of("students_programme_id",
                List.of(studentsProgrammeId))));
    }

    public String usedSum() {
        return requestWithAccessToken(requestFactory.get("services/credits/used_sum"));
    }
}
