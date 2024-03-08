package pl.wmsdev.usos4j.api.cards;

import com.github.scribejava.core.oauth.OAuth10aService;
import pl.wmsdev.usos4j.client.ScribeOAuthRequestFactory;
import pl.wmsdev.usos4j.client.UsosUserAPIDefinition;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.cards.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UsosCardsAPI extends UsosUserAPIDefinition {


    public UsosCardsAPI(OAuth10aService oAuthService, ScribeOAuthRequestFactory requestFactory, UsosAccessToken accessToken) {
        super(oAuthService, requestFactory, accessToken);
    }

    public UsosCardsOrderReply makeMcardOrder() {
        return requestWithAccessToken(requestFactory.get("services/cards/make_mcard_order"), UsosCardsOrderReply.class);
    }

    public UsosCardsMcard mcard() {
        return requestWithAccessToken(requestFactory.get("services/cards/mcard", Map.of("fields", List.of("why_cannot_order", "why_cannot_revoke", "orders"))),
                UsosCardsMcard.class);
    }

    public UsosCardsMcardOrder mcardOrder(String orderId) {
        return requestWithAccessToken(requestFactory.get("services/cards/mcard_order", Map.of("fields",
                        List.of("id", "order_date", "status", "token", "distribution_pass", "student_number", "expiration_date"),
                        "order_id", List.of(orderId))),
                UsosCardsMcardOrder.class);
    }

    public UsosCardsRevokeMcardResponse revokeMcard(String orderId) {
        return requestWithAccessToken(requestFactory.get("services/cards/revoke_mcard", Map.of("order_id", List.of(orderId))),
                UsosCardsRevokeMcardResponse.class);
    }

    public List<UsosCardsUser> user() {
        return Arrays.asList(requestWithAccessToken(requestFactory.get("services/cards/user"), UsosCardsUser[].class));
    }

}
