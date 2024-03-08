package pl.wmsdev.usos4j.api.terms;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosServerAPIDefinition;
import pl.wmsdev.usos4j.model.terms.*;

import java.util.List;
import java.util.Map;

public class UsosTermsAPI extends UsosServerAPIDefinition {

    public UsosTermsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory) {
        super(oAuthService, requestFactory);
    }

    public List<UsosTerm> search(UsosTermsSearchParams params) {
        return request(requestFactory.get("services/terms/search", params), List.class);
    }

    public UsosTerm term(UsosTermParams params) {
        return request(requestFactory.get("services/terms/term", params), UsosTerm.class);
    }

    public Map<String, UsosTerm> terms(UsosTermsParams params) {
        return request(requestFactory.get("services/terms/terms", params), Map.class);
    }

    public List<UsosTerm> termsIndex(UsosTermIndexParams params) {
        return request(requestFactory.get("services/terms/terms_index", params), List.class);
    }
}
