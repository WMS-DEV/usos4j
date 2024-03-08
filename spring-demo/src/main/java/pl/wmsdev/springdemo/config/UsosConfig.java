package pl.wmsdev.springdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.client.UsosServerAPI;

@Configuration
public class UsosConfig {

    @Value("${usos.baseUrl}")
    private String baseUrl;

    @Value("${usos.consumerKey}")
    private String consumerKey;

    @Value("${usos.consumerSecret}")
    private String consumerSecret;

    @Bean
    public Usos usos() {
        return Usos.builder()
                .baseUrl(baseUrl)
                .consumerKey(consumerKey)
                .consumerSecret(consumerSecret)
                .build();
    }

    @Bean
    public UsosServerAPI usosServerApi(Usos usos) {
        return usos.getServerApi();
    }

}
