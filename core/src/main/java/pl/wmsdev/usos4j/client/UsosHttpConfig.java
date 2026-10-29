package pl.wmsdev.usos4j.client;

import com.github.scribejava.core.httpclient.jdk.JDKHttpClient;
import com.github.scribejava.core.httpclient.jdk.JDKHttpClientConfig;
import lombok.Builder;

import java.net.Proxy;
import java.time.Duration;

@Builder
public record UsosHttpConfig(Duration connectTimeout, Duration readTimeout, Proxy proxy, Boolean followRedirects, String userAgent) {

    public UsosHttpConfig {
        timeoutMillis(connectTimeout);
        timeoutMillis(readTimeout);
    }

    JDKHttpClient createClient() {
        var config = JDKHttpClientConfig.defaultConfig();
        if (connectTimeout != null) config.setConnectTimeout(timeoutMillis(connectTimeout));
        if (readTimeout != null) config.setReadTimeout(timeoutMillis(readTimeout));
        if (proxy != null) config.setProxy(proxy);
        if (followRedirects != null) config.setFollowRedirects(followRedirects);
        return new JDKHttpClient(config);
    }

    private static Integer timeoutMillis(Duration timeout) {
        if (timeout == null) return null;
        if (timeout.isNegative() || timeout.isZero() || timeout.compareTo(Duration.ofMillis(Integer.MAX_VALUE)) > 0
                || timeout.compareTo(Duration.ofMillis(1)) < 0) {
            throw new IllegalArgumentException("HTTP timeouts must be between 1 and " + Integer.MAX_VALUE + " milliseconds");
        }
        return Math.toIntExact(timeout.toMillis());
    }
}
