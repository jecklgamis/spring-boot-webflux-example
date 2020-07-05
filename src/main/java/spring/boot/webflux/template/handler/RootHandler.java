package spring.boot.webflux.template.handler;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class RootHandler {
    @Value("${application.name}")
    private String appName;

    public Mono<ServerResponse> root(ServerRequest request) {
        ImmutableMap<Object, Object> entity = ImmutableMap.builder()
                .put("name", appName)
                .put("java.version", System.getProperty("java.version"))
                .put("now", LocalDateTime.now())
                .build();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(entity));
    }

}
