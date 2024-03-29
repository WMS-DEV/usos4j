package pl.wmsdev.usos4j.api.mailing;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosServerAPIDefinition;
import pl.wmsdev.usos4j.model.mailing.UsosMailingSystemStatusServer;

import java.util.List;
import java.util.Map;

public class UsosMailingServerAPI extends UsosServerAPIDefinition {

    public UsosMailingServerAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory) {
        super(oAuthService, requestFactory);
    }

    public UsosMailingSystemStatusServer systemStatus() {
        return request(requestFactory.get("services/mailing/system_status", Map.of("fields",
                List.of("total_pending_message_count", "daemon_running"))), UsosMailingSystemStatusServer.class);
    }

}
