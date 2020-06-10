package spring.boot.webflux.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RootRouter {

    @Bean
    public RouterFunction<ServerResponse> route(RootHandler rootHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/"), rootHandler::root);
    }
}