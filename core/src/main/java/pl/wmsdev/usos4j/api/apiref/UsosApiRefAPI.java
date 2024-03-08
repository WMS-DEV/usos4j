package pl.wmsdev.usos4j.api.apiref;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosServerAPIDefinition;
import pl.wmsdev.usos4j.model.apiref.UsosApiRefMethod;
import pl.wmsdev.usos4j.model.apiref.UsosApiRefMethodIndex;
import pl.wmsdev.usos4j.model.apiref.UsosApiRefModule;
import pl.wmsdev.usos4j.model.apiref.UsosApiRefScopes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosApiRefAPI extends UsosServerAPIDefinition {

    public UsosApiRefAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory) {
        super(oAuthService, requestFactory);
    }

    public UsosApiRefMethod method(String name) {
        return request(requestFactory.get("services/apiref/method",
                Map.of("fields", List.of("name", "short_name", "description",
                        "brief_description", "ref_url", "auth_options", "arguments",
                        "returns", "errors", "result_fields", "beta", "deprecated", "is_internal"),
                        "name", List.of(name))),
                UsosApiRefMethod.class);
    }

    public List<UsosApiRefMethodIndex> methodIndex() {
        return Arrays.asList(request(requestFactory.get("services/apiref/method_index"), UsosApiRefMethodIndex[].class));
    }

    public UsosApiRefModule module(String name) {
        return request(requestFactory.get("services/apiref/module", Map.of("name", List.of(name))), UsosApiRefModule.class);
    }

    public List<UsosApiRefScopes> scopes() {
        return Arrays.asList(request(requestFactory.get("services/apiref/scopes"), UsosApiRefScopes[].class));
    }

}
