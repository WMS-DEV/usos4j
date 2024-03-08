package pl.wmsdev.usos4j.model.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class UsosAuthToken<T> {
    protected final String token;
    protected final String tokenSecret;

    public abstract T toFrameworkToken();
}
