package spring.boot.webflux.template.handler;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProbeHandler {
    public Mono<ServerResponse> ready(ServerRequest request) {
        ImmutableMap<Object, Object> entity = ImmutableMap.builder().put("message", "I'm ready!").build();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(entity));
    }

    public Mono<ServerResponse> live(ServerRequest request) {
        ImmutableMap<Object, Object> entity = ImmutableMap.builder().put("message", "I'm alive!").build();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(entity));
    }

}



