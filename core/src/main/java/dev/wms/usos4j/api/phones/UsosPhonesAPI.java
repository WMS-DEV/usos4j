package dev.wms.usos4j.api.phones;

import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.client.ScribeOAuthRequestFactory;
import dev.wms.usos4j.client.UsosUserAPIDefinition;
import dev.wms.usos4j.docs.NotTested;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.phones.UsosPhonesPhoneNumber;
import dev.wms.usos4j.model.phones.UsosPhonesPhoneType;

import java.util.List;
import java.util.Map;

import static dev.wms.usos4j.docs.NotTestedReason.NO_ACCESS;

@NotTested(reason = NO_ACCESS)
public class UsosPhonesAPI extends UsosUserAPIDefinition implements UsosPhonesServerAPI {

    public UsosPhonesAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    @Override
    public UsosPhonesPhoneType phoneType(String id) {
        return request(requestFactory.get("services/phones/phone_type", Map.of("id", List.of(id), "fields", List.of("id", "description"))), UsosPhonesPhoneType.class);
    }

    public UsosPhonesPhoneNumber phoneNumber(String phoneId) {
        return requestWithAccessToken(requestFactory.get("services/phones/phone_number", Map.of("fields", List.of("number", "comment", "phone_type"),
                "phone_id", List.of(phoneId))), UsosPhonesPhoneNumber.class);
    }

}
