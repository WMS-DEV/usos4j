package pl.wmsdev.usos4j.api.courses;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.courses.UsosCoursesUser;

import java.util.List;
import java.util.Map;

public class UsosCoursesAPI extends UsosUserAPIDefinition {

    public UsosCoursesAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public UsosCoursesUser user() {
        return requestWithAccessToken(requestFactory.get("services/courses/user",
                Map.of("active_terms_only", List.of("false"))), UsosCoursesUser.class);
    }

}
