package pl.wmsdev.usos4j.api.generic;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosServerAPIDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UsosGenericServerAPI extends UsosServerAPIDefinition {

    public UsosGenericServerAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory) {
        super(oAuthService, requestFactory);
    }

    public <T> T request(String url, Map<String, Collection<String>> params, Class<T> type) {
        return request(requestFactory.get(url, params), type);
    }

    public <T> T request(String url, Map<String, Collection<String>> params, Class<T> type, String... fields) {
        if(fields.length != 0) {
            var map = new HashMap<>(params);
            map.put("fields", Arrays.asList(fields));
            return request(url, map, type);
        }
        return request(url, params, type);
    }

    public <T> T request(String url, Class<T> type, String... fields) {
        return request(url, Map.of(), type, fields);
    }

}
