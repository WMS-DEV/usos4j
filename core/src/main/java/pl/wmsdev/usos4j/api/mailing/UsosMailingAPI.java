package pl.wmsdev.usos4j.api.mailing;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.mailing.UsosMailingSystemStatusClient;

import java.util.List;
import java.util.Map;

public class UsosMailingAPI extends UsosUserAPIDefinition {

    public UsosMailingAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public UsosMailingSystemStatusClient systemStatus() {
        return requestWithAccessToken(requestFactory.get("services/mailing/system_status", Map.of("fields",
                List.of("total_pending_message_count", "my_pending_message_count", "daemon_running"))), UsosMailingSystemStatusClient.class);
    }

}
