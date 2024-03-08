package dev.wms.usos4j.model.auth;

import com.github.scribejava.core.model.OAuth1RequestToken;

public class UsosRequestToken extends UsosAuthToken<OAuth1RequestToken> {

    public UsosRequestToken(String token, String tokenSecret) {
        super(token, tokenSecret);
    }

    public static UsosRequestToken from(OAuth1RequestToken requestToken) {
        return new UsosRequestToken(requestToken.getToken(), requestToken.getTokenSecret());
    }

    @Override
    public OAuth1RequestToken toFrameworkToken() {
        return new OAuth1RequestToken(token, tokenSecret);
    }

}
