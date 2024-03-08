package pl.wmsdev.usos4j.client;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class UsosOAuthService extends OAuth10aService {

    public UsosOAuthService(DefaultApi10a api, String apiKey, String apiSecret, String callback, String scope, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig, HttpClient httpClient) {
        super(api, apiKey, apiSecret, callback, scope, debugStream, userAgent, httpClientConfig, httpClient);
    }

    @Override
    public Response execute(OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
        if(request.getOauthParameters().isEmpty()) {
            addOAuthParams(request, "");
            appendSignature(request);
        }
        return super.execute(request);
    }
}
