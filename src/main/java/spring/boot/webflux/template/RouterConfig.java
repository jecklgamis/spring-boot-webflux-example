package spring.boot.webflux.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import spring.boot.webflux.template.handler.FileUploadHandler;
import spring.boot.webflux.template.handler.KeyValueStoreHandler;
import spring.boot.webflux.template.handler.RootHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class RouterConfig implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> route(RootHandler rootHandler,
                                                FileUploadHandler fileUploadHandler,
                                                KeyValueStoreHandler keyValueStoreHandler) {
        return RouterFunctions.route()
                .GET("/", accept(MediaType.ALL), rootHandler::root)
                .POST("/upload", accept(MediaType.MULTIPART_FORM_DATA), fileUploadHandler::upload)
                .POST("/kv/{key}", accept(MediaType.TEXT_PLAIN), keyValueStoreHandler::store)
                .GET("/kv/{key}", keyValueStoreHandler::retrieve)
                .DELETE("/kv/{key}", keyValueStoreHandler::delete)
        .build();
        }

}