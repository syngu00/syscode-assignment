package hu.syngu00.syscodeassignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.Collections;

@Configuration
public class WebClientConfig {

    @Value("${address-service.url:http://localhost:8081}")
    private String addressServiceUrl;

    @Value("${address-service.user:user}")
    private String user;

    @Value("${address-service.password:password}")
    private String password;

    @Bean
    public WebClient addressWebClient() {
        byte[] credentials = String.format("%s:%s", user, password).getBytes();
        String auth = String.format("Basic %s", Base64.getEncoder().encodeToString(credentials));
        return WebClient.builder()
                .baseUrl(addressServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", auth)
                .defaultUriVariables(Collections.singletonMap("url", addressServiceUrl))
                .build();
    }


}
