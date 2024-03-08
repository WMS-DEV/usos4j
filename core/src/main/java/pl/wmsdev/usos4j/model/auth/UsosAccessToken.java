package pl.wmsdev.usos4j.model.auth;

import com.github.scribejava.core.model.OAuth1AccessToken;

public class UsosAccessToken extends UsosAuthToken<OAuth1AccessToken> {

    public UsosAccessToken(String token, String tokenSecret) {
        super(token, tokenSecret);
    }

    public static UsosAccessToken from(OAuth1AccessToken token) {
        return new UsosAccessToken(token.getToken(), token.getTokenSecret());
    }

    @Override
    public OAuth1AccessToken toFrameworkToken() {
        return new OAuth1AccessToken(token, tokenSecret);
    }
}
