package dev.wms.usos4j.api.plctests;

import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.client.ScribeOAuthRequestFactory;
import dev.wms.usos4j.client.UsosUserAPIDefinition;
import dev.wms.usos4j.docs.Beta;
import dev.wms.usos4j.docs.NotTested;
import dev.wms.usos4j.docs.NotTestedReason;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.common.FieldSelector;
import dev.wms.usos4j.model.plctests.UsosPlacementTest;
import dev.wms.usos4j.model.plctests.UsosPlacementTestResult;
import dev.wms.usos4j.model.plctests.UsosPlacementTestsParams;
import dev.wms.usos4j.model.plctests.UsosPlacementTestsResultParams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@NotTested(reason = NotTestedReason.NO_DATA)
public class UsosPlacementTestsAPI extends UsosUserAPIDefinition implements UsosPlacementTestsServerAPI {

    public UsosPlacementTestsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Override
    @Beta
    public UsosPlacementTest test(UsosPlacementTestsParams params) {
        return request(requestFactory.get(
                "services/plctests/test", params,
                FieldSelector.fromRequest(UsosPlacementTest.class)
        ), UsosPlacementTest.class);
    }

    @Beta
    public UsosPlacementTestResult testResult(UsosPlacementTestsResultParams params) {
        return requestWithAccessToken(requestFactory.get(
                "services/plctests/test_result", params,
                FieldSelector.fromRequest(UsosPlacementTestResult.class)
            ), UsosPlacementTestResult.class);
    }

    @Override
    @Beta
    public List<String> testsIndex() {
        return Arrays.asList(request(requestFactory.get(
                "services/plctests/tests_index"
        ), String[].class));
    }

    @Beta
    public Map<String, UsosPlacementTestResult> user() {
        return  requestWithAccessToken(requestFactory.get(
                "services/plctests/user",
                FieldSelector.fromRequest(UsosPlacementTestResult.class)
        ), Map.class);
    }
}
