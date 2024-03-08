package dev.wms.springsecuritydemo.config;

import dev.wms.usos4j.client.Usos;
import dev.wms.usos4j.client.UsosServerAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsosConfig {

    @Value("${usos.baseUrl}")
    private String baseUrl;

    @Value("${usos.consumerKey}")
    private String consumerKey;

    @Value("${usos.consumerSecret}")
    private String consumerSecret;

    @Value("${callback.url}")
    private String callbackUrl;


    @Bean
    public Usos usos() {
        return Usos.builder()
                .baseUrl(baseUrl)
                .consumerKey(consumerKey)
                .consumerSecret(consumerSecret)
                .callbackUrl(callbackUrl)
                .build();
    }

    @Bean
    public UsosServerAPI usosServerApi(Usos usos) {
        return usos.getServerApi();
    }

}
