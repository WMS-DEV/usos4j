package pl.wmsdev.usos4j.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth10aService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.wmsdev.usos4j.utils.UsosDeserializationUtils;

@RequiredArgsConstructor
@Slf4j
public abstract class UsosServerAPIDefinition {

    protected final OAuth10aService oAuthService;
    protected final ScribeOAuthRequestFactory requestFactory;

    @SneakyThrows
    protected String request(OAuthRequest request) {
        String result = oAuthService.execute(request).getBody();
        log.debug(result);
        return result;
    }

    @SneakyThrows
    protected <T> T request(OAuthRequest request, Class<T> responseClazz) {
        log.debug("Request to " + request.getCompleteUrl());
        return UsosDeserializationUtils.deserialize(request(request), responseClazz);
    }

    @SneakyThrows
    protected <T> T request(OAuthRequest request, TypeReference<T> typeReference) {
        log.debug("Request to " + request.getCompleteUrl());
        return UsosDeserializationUtils.deserialize(request(request), typeReference);
    }

}
