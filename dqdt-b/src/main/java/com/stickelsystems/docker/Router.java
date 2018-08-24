package com.stickelsystems.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {

    @Autowired
    private Handler handler;

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return route(
            GET("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
            handler::hello);

    }
}
