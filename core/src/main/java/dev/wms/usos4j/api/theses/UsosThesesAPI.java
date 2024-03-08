package dev.wms.usos4j.api.theses;

import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.client.ScribeOAuthRequestFactory;
import dev.wms.usos4j.client.UsosUserAPIDefinition;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.common.*;
import dev.wms.usos4j.model.common.UsosThesesUserData;
import dev.wms.usos4j.model.theses.*;

import java.util.Map;

public class UsosThesesAPI extends UsosUserAPIDefinition {

    public UsosThesesAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public UsosThesesSearch search(UsosThesesSearchParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/theses/search", params, FieldSelector.fromRequest(UsosThesesSearch.class)
        ), UsosThesesSearch.class);
    }

    public UsosThesesSearchHistoryAffect searchHistoryAffect(UsosThesesSearchHistoryAffectParams params) {
        return requestWithAccessToken(requestFactory.get(
            "services/theses/search_history_affect", params
        ), UsosThesesSearchHistoryAffect.class);
    }

    public UsosThesis thesis(UsosThesisParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/theses/thesis", params, FieldSelector.fromRequest(UsosThesis.class)
        ), UsosThesis.class);
    }

    public Map<String, UsosThesis> theses(UsosThesesParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/theses/theses", params, FieldSelector.fromRequest(UsosThesis.class)
        ), Map.class);
    }

    public UsosThesesUserData user(UsosThesesUserParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/theses/user", params, FieldSelector.fromRequest(UsosThesesUserData.class)
        ), UsosThesesUserData.class);
    }

    public Map<String, UsosThesesUserData> users(UsosThesesUsersParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/theses/users", params, FieldSelector.fromRequest(UsosThesesUserData.class)
        ), Map.class);
    }

}
