package dev.wms.usos4j.api.apisrv;

import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.client.ScribeOAuthRequestFactory;
import dev.wms.usos4j.client.UsosServerAPIDefinition;
import dev.wms.usos4j.model.apisrv.UsosApiSrvConsumer;
import dev.wms.usos4j.model.apisrv.UsosApiSrvInstallation;
import dev.wms.usos4j.model.apisrv.UsosApiSrvShortInstallation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosApiSrvAPI extends UsosServerAPIDefinition {


    public UsosApiSrvAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory) {
        super(oAuthService, requestFactory);
    }


    public UsosApiSrvConsumer consumer() {
        return request(requestFactory.get("services/apisrv/consumer",
                Map.of("fields", List.of("name", "url", "email", "date_registered",
                        "administrative_methods", "token_scopes"))), UsosApiSrvConsumer.class);
    }

    public UsosApiSrvInstallation installation() {
        return request(requestFactory.get("services/apisrv/installation", Map.of("fields", List.of("base_url", "version",
                "machine_version", "usos_schema_version", "institution_name", "institution",
                "contact_emails", "schac_id", "mcards_support"))), UsosApiSrvInstallation.class);
    }

    public List<UsosApiSrvShortInstallation> installations() {
        return Arrays.asList(request(requestFactory.get("services/apisrv/installations"), UsosApiSrvShortInstallation[].class));
    }

    public LocalDateTime now() {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        return LocalDateTime.parse(request(requestFactory.get("services/apisrv/now"))
                .replace("\"", ""), formatter);
    }

}
