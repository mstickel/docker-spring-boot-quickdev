package com.stickelsystems.docker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handler {

	@Value("${message}")
	private String message;

	public Mono<ServerResponse> hello(ServerRequest serverRequest) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(message).map(m -> {
					Message messageObj = new Message();
					messageObj.setMessage(m);
					return messageObj;
				}), Message.class);
	}
}
