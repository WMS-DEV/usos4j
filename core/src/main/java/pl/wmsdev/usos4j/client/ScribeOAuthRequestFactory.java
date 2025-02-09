package pl.wmsdev.usos4j.client;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import lombok.RequiredArgsConstructor;
import pl.wmsdev.usos4j.model.common.FieldSelector;
import pl.wmsdev.usos4j.model.common.UsosParams;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScribeOAuthRequestFactory {

    private final String BASE_URL;

    public OAuthRequest get(String url) {
        return create(Verb.GET, url, Collections.emptyMap());
    }

    public OAuthRequest get(String url, UsosParams params) {
        return get(url, params.getParams());
    }

    public OAuthRequest get(String url, FieldSelector fieldSelector) {
        return get(url, Map.of("fields", List.of(fieldSelector.getSelectedFields())));
    }

    public OAuthRequest get(String url, UsosParams params, FieldSelector fieldSelector) {
        return get(url, params.getParams(fieldSelector));
    }

    public OAuthRequest get(String url, Map<String, Collection<String>> params) {
        return create(Verb.GET, url, params);
    }

    private OAuthRequest create(Verb method, String url, Map<String, Collection<String>> params) {
        return new OAuthRequest(method, createUrl(url, params));
    }

    private String createUrl(String url, Map<String, Collection<String>> params) {
        return BASE_URL + url + "?" + params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue().stream().distinct()
                        .collect(Collectors.joining("|")))
                .collect(Collectors.joining("&"));
    }

}
