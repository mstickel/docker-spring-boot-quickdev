package com.stickelsystems.docker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    @Autowired
    private BClient bClient;

    @Value("${message}")
    private String message;

    public Mono<ServerResponse> hello(ServerRequest serverRequest) {
        logger.info("Received hello request");
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(bClient.getMessageFromB().map(b -> {
                HelloResponse helloResponse = new HelloResponse();
                helloResponse.setMessageFromA(message);
                helloResponse.setMessageFromB(b);
                return helloResponse;
            }), HelloResponse.class);
    }
}
