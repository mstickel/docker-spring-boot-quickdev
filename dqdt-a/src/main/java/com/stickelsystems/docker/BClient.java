package com.stickelsystems.docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BClient implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(BClient.class);

    private WebClient webClient;

    @Value("${dqdt-b.url}")
    private String bUrl;

    public Mono<String> getMessageFromB() {
        return webClient
            .get()
            .uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Message.class)
            .map(m -> m.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("bClient url: {}", bUrl);
        webClient = WebClient.create(bUrl);
    }
}
